package de.ppi.here.tcu.composite.prepare;

import static de.ppi.here.tcu.util.StringUtil.containsLowerCase;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.result.ValidationInformation;

/**
 * Setzt die Betreiber-Id auf Upper-Case
 */
@Service
public class OperatorUppercasePreparationService  implements InsertPreparationService<Operator> {

    @Override
    public List<ValidationInformation> prepareInsert(final Operator businessObject) {

        if (!StringUtils.isEmpty(businessObject.getId()) && containsLowerCase(businessObject.getOperatorId())) {
            businessObject.setOperatorId(businessObject.getOperatorId().toUpperCase());

            return Collections.singletonList(new ValidationInformation("COMMON_MESSAGES_CHANGE_FROM_LOWER_TO_UPPERCASE"));
        }

        return Collections.emptyList();
    }

}
