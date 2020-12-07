package de.ppi.here.tcu.adminservice.duplicate;

import static de.ppi.here.tcu.util.StringUtil.containsLowerCase;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.OperatorDao;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.service.OperatorInfrastructureService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.OperatorValidator;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer Betreiber-Entität in die Datenbank
 */
@Service
public class OperatorAdministrationService implements AdministrationService<Operator> {

    @Autowired
    private OperatorValidator operatorValidator;

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private ChangeDataIterator<Operator> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<Operator> changeRecordProtocolService;

    @Autowired
    private OperatorInfrastructureService operatorInfrastructureService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final Operator businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations = new ArrayList<>();

        // Beim Einfügen ggf. alles in Großbuchstaben umwandeln
        if (!StringUtils.isEmpty(businessObject.getOperatorId())
            && containsLowerCase(businessObject.getOperatorId())) {
            businessObject.setOperatorId(businessObject.getOperatorId().toUpperCase());

            validationInformations
                .add(new ValidationInformation("COMMON_MESSAGES_CHANGE_FROM_LOWER_TO_UPPERCASE"));
        }

        validationInformations
            .addAll(operatorValidator.validate(businessObject, ValidationContext.createInsert()));

        if(operatorDao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final Operator persistent = operatorDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new Operator(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            businessObject, diffWrapperList, dialogUserIdInformation, null, false);

        operatorInfrastructureService.createOperatorInfrastructure(businessObject, businessObject.getId(),
            dialogUserIdInformation);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("OPERATOR_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }

}
