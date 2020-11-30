package de.ppi.here.tcu.adminservice;

import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.validation.ConstraintViolationException;


/**
 * Service zum Einfügen einer Entität in die Datenbank
 *
 * @param <T> Typ der Entität
 */
public interface AdministrationService<T extends Entity> {
    MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException;
}
