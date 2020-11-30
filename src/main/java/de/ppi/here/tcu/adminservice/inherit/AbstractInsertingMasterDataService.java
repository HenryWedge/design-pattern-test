package de.ppi.here.tcu.adminservice.inherit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.composite.precondition.PreconditionNotFulfilledException;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.ConstraintViolationException;

/**
 * Abstrakter Service zum Einfügen einer Entität in die Datenbank
 *
 * @param <T> Typ der Entität
 */
public abstract class AbstractInsertingMasterDataService<T extends Entity>
    implements AdministrationService<T> {

    public final MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation)
            throws DuplicateEntityException, PreconditionNotFulfilledException, ConstraintViolationException {

        checkInsertPreconditions(businessObject);

        final List<ValidationInformation> validationInformations = new ArrayList<>();

        validationInformations.addAll(prepareForInsert(businessObject));
        validationInformations.addAll(validateForInsert(businessObject, dialogUserIdInformation));

        getBeanFromDatabaseForCreation(businessObject)
            .orElseThrow(() -> new DuplicateEntityException(businessObject));

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            internalInsert(businessObject, dialogUserIdInformation);

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }


    protected abstract void checkInsertPreconditions(T businessObject) throws PreconditionNotFulfilledException;


    protected abstract List<ValidationInformation> prepareForInsert(T businessObject);


    protected abstract List<ValidationInformation> validateForInsert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException;


    protected abstract Optional<T> getBeanFromDatabaseForCreation(T businessObject);


    protected abstract MasterDataAdministrationOperationSuccessServiceResult internalInsert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation);

}
