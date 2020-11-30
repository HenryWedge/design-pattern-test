package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.demo.validation.ValidationContext;
import de.ppi.here.tcu.entity.FtpFileType;
import de.ppi.here.tcu.result.ValidationInformation;

/**
 * Mock-Service
 *
 * Validiert FtpFileType-Entitäten
 */
@Service
public class FtpFileValidator implements Validator<FtpFileType> {

    @Override
    public List<ValidationInformation> validate(final FtpFileType businessObject, final ValidationContext insert)
        throws ConstraintViolationException {
        return Collections.emptyList();
    }
}
