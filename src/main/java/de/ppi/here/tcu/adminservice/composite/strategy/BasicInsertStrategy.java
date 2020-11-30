package de.ppi.here.tcu.adminservice.composite.strategy;

import java.util.List;
import org.springframework.stereotype.Component;
import de.ppi.here.tcu.composite.inserter.Inserter;
import de.ppi.here.tcu.dao.Dao;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.ValidationContext;
import de.ppi.here.tcu.validation.Validator;

@Component
public class BasicInsertStrategy<T extends Entity> {

    public MasterDataAdministrationOperationSuccessServiceResult insert(final T businessObject,
        final DialogUserIdInformation dialogUserIdInformation, final Validator<T> validator, final Dao<T> dao,
        final Inserter<T> inserter) throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            validator.validate(businessObject, ValidationContext.createInsert());

        dao.findById(businessObject.getId()).orElseThrow(() -> new DuplicateEntityException(businessObject));

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            inserter.insert(businessObject, dialogUserIdInformation);

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;

    }
}
