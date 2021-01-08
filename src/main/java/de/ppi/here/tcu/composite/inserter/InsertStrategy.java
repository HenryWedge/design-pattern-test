package de.ppi.here.tcu.composite.inserter;

import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;

/**
 * Einfüge-Ablauf
 * @param <T> Typ des einzufügenden Elements
 */
public interface InsertStrategy<T extends Entity> {

    MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation) throws DuplicateEntityException;

}
