package de.ppi.here.tcu.composite.prepare;

import static de.ppi.here.tcu.util.StringUtil.containsLowerCase;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.ValidationInformation;


/**
 * Setzt die Mandaten-Id auf Upper-Case
 */
@Service
public class MandatorUppercasePreparationService implements InsertPreparationService<Mandator> {

    @Override
    public List<ValidationInformation> prepareInsert(final Mandator businessObject) {

        if (!StringUtils.isEmpty(businessObject.getId()) && containsLowerCase(businessObject.getMandatorId())) {
            businessObject.setMandatorId(businessObject.getMandatorId().toUpperCase());

            return Collections
                .singletonList(new ValidationInformation("COMMON_MESSAGES_CHANGE_FROM_LOWER_TO_UPPERCASE"));
        }

        return Collections.emptyList();
    }

}
