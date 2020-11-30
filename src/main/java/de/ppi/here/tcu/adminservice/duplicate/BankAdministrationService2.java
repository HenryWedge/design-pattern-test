package de.ppi.here.tcu.adminservice.duplicate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.BankDao;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.BankValidator;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer Bank-Entität in die Datenbank
 */
@Service
public class BankAdministrationService2 implements AdministrationService<Bank> {

    @Autowired
    private BankValidator bankValidator;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private ChangeDataIterator<Bank> bankChangeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<Bank> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    public final MasterDataAdministrationOperationSuccessServiceResult insert(Bank businessObject,
        DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            bankValidator.validate(businessObject, ValidationContext.createInsert());

        if (bankDao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final Bank persistent = bankDao.makePersistent(businessObject);

        final List<ChangeData> diffList = bankChangeDataIterator.getDiffDataSet(new Bank(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService
            .createAndPersistCreationChangeRecord(businessObject, diffList, dialogUserIdInformation, null, false);
        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("BANK_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }

}
