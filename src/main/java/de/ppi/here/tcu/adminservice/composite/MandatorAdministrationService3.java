package de.ppi.here.tcu.adminservice.composite;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy;
import de.ppi.here.tcu.composite.inserter.MandatorInserter;
import de.ppi.here.tcu.composite.precondition.DefaultMandatorCheckService;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.composite.prepare.MandatorUppercasePreparationService;
import de.ppi.here.tcu.dao.MandatorDao;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.MandatorValidator;


/**
 * Service zum Einfügen einer Manstaten-Entität in die Datenbank
 */
@Service
public class MandatorAdministrationService3 implements AdministrationService<Mandator> {

    @Autowired
    private MandatorInserter inserter;

    @Autowired
    private MandatorDao mandatorDao;

    @Autowired
    private MandatorValidator mandatorValidator;

    @Autowired
    private DefaultMandatorCheckService defaultMandatorCheckService;

    @Autowired
    private MandatorUppercasePreparationService mandatorUppercasePreparationService;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(Mandator businessObject,
        DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException {

        List<ValidationInformation> validationInformations =
            defaultMandatorCheckService.checkPrecondition(businessObject);
        validationInformations.addAll(mandatorUppercasePreparationService.prepareInsert(businessObject));

        MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new BasicInsertStrategy<>(mandatorValidator, mandatorDao, inserter).insert(businessObject,
                dialogUserIdInformation);

        for (ValidationInformation validationInformation : validationInformations) {
            serviceResult.addSubServiceResult(validationInformation);
        }

        return serviceResult;
    }
}
