package de.ppi.here.tcu.composite.precondition;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.tcu.dao.MandatorDao;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.ConfigParameterService;

/**
 * Service, der prüft, ob ein Standard-Mandant zu einem Mandaten existiert
 */
@Service
public class DefaultMandatorCheckService implements PreconditionCheckService<Mandator> {

    @Autowired
    private ConfigParameterService configParameterService;

    @Autowired
    private MandatorDao mandatorDao;

    @Override
    public List<ValidationInformation> checkPrecondition(final Mandator businessObject)
        throws PreconditionNotFulfilledException {
        final String defaultMandatorId =
            configParameterService.getDefaultMandatorId(businessObject.getOperatorId());
        if (!StringUtils.isEmpty(defaultMandatorId)) {
            mandatorDao.findById(businessObject.getId())
                .orElseThrow(() -> new PreconditionNotFulfilledException(
                    "MANDATOR_BUSINESS_ERRORS_DEFAULT_MANDATOR_NOT_EXISTS - " + defaultMandatorId
                        + businessObject.getId()));
        }
        return Collections.emptyList();
    }
}
