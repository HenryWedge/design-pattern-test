package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy2;
import de.ppi.here.tcu.changeData.ChangeDataIteratorImpl;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInserter;
import de.ppi.here.tcu.composite.inserter.Inserter;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.BankDao;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.BankValidator;


/**
 * Service zum Einfügen einer Bank-Entität in die Datenbank
 */
@Service
public class BankAdministrationService3 implements AdministrationService<Bank> {

    @Autowired
    private ChangeRecordProtocolService<Bank> changeDataProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private BankValidator bankValidator;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private BasicInsertStrategy2<Bank> insertStrategy;

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
