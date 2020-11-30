package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.FtpApplication;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert FtpApplication-Entitäten
 */
@Service
public class FtpApplicationValidator implements Validator<FtpApplication> {

    @Override
    public List<ValidationInformation> validate(final FtpApplication businessObject,
        final ValidationContext insert) throws ConstraintViolationException {
        return Collections.emptyList();
    }

}
