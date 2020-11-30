package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.demo.validation.ValidationContext;
import de.ppi.here.tcu.entity.Bank;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert Bank-Entitäten
 */
@Service
public class BankValidator implements Validator<Bank> {

    @Override
    public List<ValidationInformation> validate(final Bank businessObject, final ValidationContext insert)
        throws ConstraintViolationException {
        return Collections.emptyList();
    }
}
