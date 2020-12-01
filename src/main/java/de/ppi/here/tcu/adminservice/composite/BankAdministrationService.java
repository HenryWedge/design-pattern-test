package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy;
import de.ppi.here.tcu.changeData.ChangeDataIteratorImpl;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInserter;
import de.ppi.here.tcu.composite.inserter.Inserter;
import de.ppi.here.tcu.dao.BankDao;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.BankValidator;
import de.ppi.here.tcu.validation.ConstraintViolationException;

/**
 * Service zum Einfügen einer Bank-Entität in die Datenbank
 */
@Service
public class BankAdministrationService implements AdministrationService<Bank> {

    @Autowired
    private ChangeRecordProtocolService<Bank> changeDataProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private BankValidator bankValidator;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private BasicInsertStrategy<Bank> insertStrategy;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final Bank businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {
        final Inserter<Bank> inserter =
            new BasicInserter<>(changeDataProtocolService, administrationProtocolEventService, new BankDao(),
                new ChangeDataIteratorImpl<>(), new Bank(), "BANK_MESSAGES_CREATED");
        return insertStrategy.insert(businessObject, dialogUserIdInformation, bankValidator, bankDao, inserter);
    }
}
