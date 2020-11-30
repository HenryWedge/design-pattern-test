package de.ppi.here.tcu.validation;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Validator für Entitäten
 *
 * @param <T>
 */
public interface Validator<T extends Entity> {

    List<ValidationInformation> validate(final T businessObject, final ValidationContext insert)
        throws ConstraintViolationException;

}
