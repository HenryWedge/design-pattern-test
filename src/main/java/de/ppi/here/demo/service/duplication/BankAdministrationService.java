package de.ppi.here.demo.service.duplication;

import org.springframework.stereotype.Service;
import de.ppi.here.demo.bo.Bank;
import de.ppi.here.demo.results.OperationResult;
import de.ppi.here.demo.results.OperationResultImpl;
import de.ppi.here.demo.service.MasterDataAdministrationService;

@Service
public class BankAdministrationService implements MasterDataAdministrationService<Bank> {

    @Override
    public OperationResult insert(Bank bank) {
        checkInsertPreconditions(bank);
        prepareForInsert(bank);
        validateForInsert(bank);
        duplicationCheck(bank);
        persistObject(bank);
        persistChangeData(bank);
        fireProtocolEvent(bank);
        return new OperationResultImpl();
    }

    private void persistObject(final Bank bank) {
    }

    private void fireProtocolEvent(final Bank bank) {
    }

    private void persistChangeData(final Bank bank) {
    }

    private void duplicationCheck(final Bank bank) {
    }

    private void validateForInsert(final Bank bank) {
    }

    private void prepareForInsert(final Bank bank) {
    }

    private void checkInsertPreconditions(Bank bank) {

    }

}
