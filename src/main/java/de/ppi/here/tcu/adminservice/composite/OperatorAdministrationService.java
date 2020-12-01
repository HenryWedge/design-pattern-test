package de.ppi.here.tcu.adminservice.composite;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy;
import de.ppi.here.tcu.composite.inserter.OperatorInserter;
import de.ppi.here.tcu.composite.prepare.OperatorUppercasePreparationService;
import de.ppi.here.tcu.dao.OperatorDao;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.OperatorValidator;


/**
 * Service zum Einfügen einer Betreiber-Entität in die Datenbank
 */
@Service
public class OperatorAdministrationService implements AdministrationService<Operator> {

    @Autowired
    private OperatorValidator validator;

    @Autowired
    private OperatorDao dao;

    @Autowired
    private OperatorInserter inserter;

    @Autowired
    private OperatorUppercasePreparationService preparationService;

    @Autowired
    private BasicInsertStrategy<Operator> basicInsertStrategy;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final Operator businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            preparationService.prepareInsert(businessObject);

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            basicInsertStrategy.insert(businessObject, dialogUserIdInformation, validator, dao, inserter);

        for (ValidationInformation validationInformation : validationInformations) {
            serviceResult.addSubServiceResult(validationInformation);
        }

        return serviceResult;
    }
}
