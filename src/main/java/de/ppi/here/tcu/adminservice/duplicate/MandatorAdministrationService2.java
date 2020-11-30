package de.ppi.here.tcu.adminservice.duplicate;

import static de.ppi.here.tcu.util.StringUtil.containsLowerCase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.service.AddInfoDataPersistenceService;
import de.ppi.here.tcu.service.AddInfoField;
import de.ppi.here.tcu.service.ConfigParameterService;
import de.ppi.here.tcu.service.MandatorInfrastructureService;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.MandatorDao;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.MandatorValidator;
import de.ppi.here.demo.validation.MandatorValidationContext;

/**
 * Service zum Einfügen einer Mandanten-Entität in die Datenbank
 */
@Service
public class MandatorAdministrationService2 implements AdministrationService<Mandator> {

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
    public MasterDataAdministrationOperationSuccessServiceResult insert(final Mandator businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
            throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException {

        final String defaultMandatorId =
            configParameterService.getDefaultMandatorId(businessObject.getOperatorId());
        if (!StringUtils.isEmpty(defaultMandatorId)) {
            mandatorDao.findById(businessObject.getId())
                .orElseThrow(() -> new PreconditionNotFulfilledException(
                    "MANDATOR_BUSINESS_ERRORS_DEFAULT_MANDATOR_NOT_EXISTS - " + defaultMandatorId
                        + businessObject.getId()));
        }

        final List<ValidationInformation> validationInformations = new ArrayList<>();

        // Beim Einfügen ggf. alles in Großbuchstaben umwandeln
        if (!StringUtils.isEmpty(businessObject.getId()) && containsLowerCase(businessObject.getMandatorId())) {
            businessObject.setMandatorId(businessObject.getMandatorId().toUpperCase());

            validationInformations
                .add(new ValidationInformation("COMMON_MESSAGES_CHANGE_FROM_LOWER_TO_UPPERCASE"));
        }

        validationInformations
            .addAll(mandatorValidator.validate(businessObject, MandatorValidationContext.createInsert()));

        mandatorDao.findById(businessObject.getId())
            .orElseThrow(() -> new DuplicateEntityException(businessObject));

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

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        addInfoDataPersistenceService.persistAddInfoToBusinessObject(businessObject.getAddInfoFieldList(),
            businessObject);

        mandatorInfrastructureService.createMandatorInfrastructureWithOrderTypeInheritance(businessObject,
            changeRecordBean.getId());

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("MANDATOR_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }

}
