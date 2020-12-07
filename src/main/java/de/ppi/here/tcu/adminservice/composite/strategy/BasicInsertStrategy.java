package de.ppi.here.tcu.adminservice.composite.strategy;

import java.util.List;
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


public class BasicInsertStrategy<T extends Entity> {

    final Validator<T> validator;

    final Dao<T> dao;

    final Inserter<T> inserter;

    public BasicInsertStrategy(final Validator<T> validator, final Dao<T> dao, final Inserter<T> inserter) {
        this.validator = validator;
        this.dao = dao;
        this.inserter = inserter;
    }

    public MasterDataAdministrationOperationSuccessServiceResult insert(final T businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            validator.validate(businessObject, ValidationContext.createInsert());

        if (dao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            inserter.insert(businessObject, dialogUserIdInformation);

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }
}
