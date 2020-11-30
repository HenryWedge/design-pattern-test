package de.ppi.here.tcu.validation;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.FtpServer;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Mock-Service
 *
 * Validiert FtpServer-Entitäten
 */
@Service
public class FtpServerValidator implements Validator<FtpServer> {

    @Override
    public List<ValidationInformation> validate(final FtpServer businessObject, final ValidationContext insert)
        throws ConstraintViolationException {
        return Collections.emptyList();
    }

}
