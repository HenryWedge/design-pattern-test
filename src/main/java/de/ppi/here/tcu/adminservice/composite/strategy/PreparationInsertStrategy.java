package de.ppi.here.tcu.adminservice.composite.strategy;

import java.util.List;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.composite.prepare.InsertPreparationService;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;

/**
 * Standard-Einfüge-Strategie mit Entitätsvorbereitung
 * @param <T>
 */
public abstract class PreparationInsertStrategy<T extends Entity> implements AdministrationService<T> {

    public MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation) throws DuplicateEntityException {

        List<ValidationInformation> validationList = getInsertPreparationService().prepareInsert(businessObject);

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            getInsertStrategy().insert(businessObject, dialogUserIdInformation);

        validationList.forEach(serviceResult::addSubServiceResult);

        return serviceResult;
    }

    protected abstract BasicInsertStrategy<T> getInsertStrategy();

    protected abstract InsertPreparationService<T> getInsertPreparationService();


}
