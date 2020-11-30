package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.demo.validation.ValidationContext;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert Mandanten-Entitäten
 */
@Service
public class MandatorValidator implements Validator<Mandator> {

    @Override
    public List<ValidationInformation> validate(final Mandator businessObject, final ValidationContext insert)
        throws ConstraintViolationException {
        return Collections.emptyList();
    }
}
