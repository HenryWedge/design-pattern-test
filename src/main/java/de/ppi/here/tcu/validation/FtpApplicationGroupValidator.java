package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert FtpApplicationGroup-Entitäten
 */
@Service
public class FtpApplicationGroupValidator implements Validator<FtpApplicationGroup> {

    @Override
    public List<ValidationInformation> validate(final FtpApplicationGroup businessObject,
        final ValidationContext insert) throws ConstraintViolationException {
        return Collections.emptyList();
    }

}
