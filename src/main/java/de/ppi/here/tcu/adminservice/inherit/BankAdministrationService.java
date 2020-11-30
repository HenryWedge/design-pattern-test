package de.ppi.here.tcu.adminservice.inherit;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.BankDao;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.BankValidator;
import de.ppi.here.demo.validation.CommonValidationContext;

/**
 * Service zum Einfügen einer Bank-Entität in die Datenbank
 */
@Service
public class BankAdministrationService extends AbstractInsertingMasterDataService<Bank> {

    @Autowired
    private BankValidator bankValidator;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private ChangeDataIterator<Bank> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<Bank> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Override
    protected void checkInsertPreconditions(final Bank businessObject) {
        // Nichts zu prüfen
    }

    @Override
    protected MasterDataAdministrationOperationSuccessServiceResult internalInsert(final Bank businessObject, final DialogUserIdInformation dialogUserIdInformation) {

        final Bank persistent = bankDao.makePersistent(businessObject);

        final List<ChangeData> diffList =
                changeDataIterator.getDiffDataSet(new Bank(), persistent);

        final ChangeRecord changeRecordBean =
                changeRecordProtocolService.createAndPersistCreationChangeRecord(businessObject,
                        diffList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult("BANK_MESSAGES_CREATED");
    }

    @Override
    protected List<ValidationInformation> prepareForInsert(final Bank businessObject) {
        return Collections.emptyList();
    }

    @Override
    protected Optional<Bank> getBeanFromDatabaseForCreation(final Bank businessObject) {
        return bankDao.findById(businessObject.getId());
    }

    @Override
    protected List<ValidationInformation> validateForInsert(final Bank businessObject, final DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException {
        return bankValidator.validate(businessObject, CommonValidationContext.createInsert());
    }
}
