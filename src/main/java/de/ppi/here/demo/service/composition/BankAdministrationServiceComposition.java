package de.ppi.here.demo.service.composition;

import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.bo.Bank;
import de.ppi.here.demo.dao.BankDao;
import de.ppi.here.demo.results.OperationResult;
import de.ppi.here.demo.results.OperationResultImpl;
import de.ppi.here.demo.service.MasterDataAdministrationService;
import de.ppi.here.demo.service.composition.changedata.ChangeDataPersister;
import de.ppi.here.demo.service.composition.duplicationcheck.DuplicationChecker;
import de.ppi.here.demo.service.composition.persistence.PersistenceService;
import de.ppi.here.demo.service.composition.preparation.PreconditionCheckService;
import de.ppi.here.demo.service.composition.preparation.PreparationService;
import de.ppi.here.demo.service.composition.protocol.ProtocolService;
import de.ppi.here.demo.service.composition.validation.ValidationService;
import de.ppi.here.demo.service.util.ValidationInformation;

@Service
public class BankAdministrationServiceComposition implements MasterDataAdministrationService<Bank> {

    private PreconditionCheckService<Bank> preconditionCheckService;
    private ValidationService<Bank> bankValidationService;
    private PreparationService preparationService;
    private PersistenceService<Bank> persistenceService;
    private DuplicationChecker<Bank> duplicationChecker;
    private ProtocolService protocolService;
    private ChangeDataPersister<Bank> changeDataPersister;

    @Override
    public OperationResult insert(Bank bank) {
        preconditionCheckService.checkPrecondition(bank);
        List<ValidationInformation> validationInformations = bankValidationService.validate(bank);
        validationInformations.addAll(duplicationChecker.checkDuplicate(bank));
        persistenceService.persistObject(new BankDao(), bank);
        changeDataPersister.protocolChangeData(bank);
        protocolService.fireProtocolEvent();
        return new OperationResultImpl();
    }
}
