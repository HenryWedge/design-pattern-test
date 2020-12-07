package de.ppi.here.tcu.adminservice.inherit;

import static de.ppi.here.tcu.util.StringUtil.containsLowerCase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.MandatorDao;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AddInfoDataPersistenceService;
import de.ppi.here.tcu.service.AddInfoField;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.service.ConfigParameterService;
import de.ppi.here.tcu.service.MandatorInfrastructureService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.MandatorValidator;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer Mandanten-Entität in die Datenbank
 */
@Service
public class MandatorAdministrationService extends AbstractInsertingMasterDataService<Mandator> {

    @Autowired
    private MandatorDao mandatorDao;

    @Autowired
    private ConfigParameterService configParameterService;

    @Autowired
    private MandatorValidator mandatorValidator;

    @Autowired
    private ChangeDataIterator<Mandator> changeDataIterator;

    @Autowired
    private ChangeDataIterator<AddInfoField> addInfoChangeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<Mandator> otherChangeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private AddInfoDataPersistenceService<Mandator> addInfoDataPersistenceService;

    @Autowired
    private MandatorInfrastructureService mandatorInfrastructureService;

    @Override
    protected void checkInsertPreconditions(final Mandator businessObject)
        throws PreconditionNotFulfilledException {
        final String defaultMandatorId =
            configParameterService.getDefaultMandatorId(businessObject.getOperatorId());
        if (!StringUtils.isEmpty(defaultMandatorId)) {
            mandatorDao.findById(businessObject.getId())
                .orElseThrow(() -> new PreconditionNotFulfilledException(
                    "MANDATOR_BUSINESS_ERRORS_DEFAULT_MANDATOR_NOT_EXISTS - " + defaultMandatorId
                        + businessObject.getId()));
        }
    }


    @Override
    protected List<ValidationInformation> validateForInsert(final Mandator businessObject,
        final DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException {
        return mandatorValidator.validate(businessObject, ValidationContext.createMandatorInsert());
    }


    @Override
    protected List<ValidationInformation> prepareForInsert(final Mandator businessObject) {
        final List<ValidationInformation> result = new ArrayList<>();

        // Beim Einfügen ggf. alles in Großbuchstaben umwandeln
        if (!StringUtils.isEmpty(businessObject.getId()) && containsLowerCase(businessObject.getMandatorId())) {
            businessObject.setMandatorId(businessObject.getMandatorId().toUpperCase());

            result.add(new ValidationInformation("COMMON_MESSAGES_CHANGE_FROM_LOWER_TO_UPPERCASE"));
        }

        return result;
    }


    @Override
    protected Optional<Mandator> getBeanFromDatabaseForCreation(final Mandator businessObject) {
        return mandatorDao.findById(businessObject.getId());
    }


    @Override
    protected MasterDataAdministrationOperationSuccessServiceResult internalInsert(final Mandator businessObject,
        final DialogUserIdInformation dialogUserIdInformation) {

        final Mandator persistent = mandatorDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new Mandator(), persistent);

        if (!businessObject.getAddInfoFieldList().isEmpty()) {
            diffWrapperList.addAll(businessObject.getAddInfoFieldList().stream()
                .map(field -> addInfoChangeDataIterator.getDiffDataSet(field, new AddInfoField()))
                .flatMap(Collection::stream).collect(Collectors.toList()));
        }

        final ChangeRecord changeRecordBean =
            otherChangeRecordProtocolService.createAndPersistCreationChangeRecord(persistent, diffWrapperList,
                dialogUserIdInformation, null, false);

        addInfoDataPersistenceService.persistAddInfoToBusinessObject(businessObject.getAddInfoFieldList(),
            businessObject);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        mandatorInfrastructureService.createMandatorInfrastructureWithOrderTypeInheritance(businessObject,
            changeRecordBean.getId());

        return new MasterDataAdministrationOperationSuccessServiceResult("MANDATOR_MESSAGES_CREATED");

    }
}
