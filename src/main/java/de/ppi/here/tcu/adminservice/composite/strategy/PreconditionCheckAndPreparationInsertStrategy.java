package de.ppi.here.tcu.adminservice.composite.strategy;

import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.composite.precondition.PreconditionCheckService;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;


/**
 * Standard-Einfüge-Strategie mit Entitätsvorbereitung und Vorbedinungsüberprüfung
 *
 * @param <T>
 */
public abstract class PreconditionCheckAndPreparationInsertStrategy<T extends Entity>
    implements AdministrationService<T> {

    public MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, PreconditionNotFulfilledException {

        getPreconditionCheckService().checkPrecondition(businessObject);

        return getInsertStrategy().insert(businessObject, dialogUserIdInformation);
    }


    protected abstract PreparationInsertStrategy<T> getInsertStrategy();

    protected abstract PreconditionCheckService<T> getPreconditionCheckService();

}
