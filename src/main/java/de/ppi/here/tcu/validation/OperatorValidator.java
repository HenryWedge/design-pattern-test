package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert Betreiber-Entitäten
 */
@Service
public class OperatorValidator implements Validator<Operator> {

    @Override
    public List<ValidationInformation> validate(final Operator businessObject, final ValidationContext insert)
        throws ConstraintViolationException {
        return Collections.emptyList();
    }
}
