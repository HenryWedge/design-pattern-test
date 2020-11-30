package de.ppi.here.tcu.adminservice.composite.strategy;

import java.util.List;
import de.ppi.here.demo.validation.CommonValidationContext;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.composite.inserter.Inserter;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.Dao;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.Validator;


/**
 * Standard-Einfüge-Strategie
 *
 * @param <T>
 */
public abstract class BasicInsertStrategy<T extends Entity> implements AdministrationService<T> {

    public MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            getValidator().validate(businessObject, CommonValidationContext.createInsert());

        getDao().findById(businessObject.getId()).orElseThrow(() -> new DuplicateEntityException(businessObject));

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            getInserter().insert(businessObject, dialogUserIdInformation);

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }
        return serviceResult;
    }


    protected abstract Validator<T> getValidator();


    protected abstract Dao<T> getDao();


    protected abstract Inserter<T> getInserter();

}
