package de.ppi.here.demo.service.inheritance;

import org.springframework.stereotype.Service;
import de.ppi.here.demo.bo.Bank;
import de.ppi.here.demo.dao.BankDao;
import de.ppi.here.demo.dao.Dao;
import de.ppi.here.demo.results.OperationResult;

@Service
public class BankAdministrationServiceInheritance extends AbstractMasterDataAdministrationService<Bank> {

    @Override
    public OperationResult insert(final Bank bank) {
        persistObject(bank);
        return null;
    }

    @Override
    protected Dao<Bank> getDao() {
        return new BankDao();
    }

}
