package de.ppi.here.demo.service.composition.preparation;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.ValidationInformation;

public interface PreparationService<T extends Entity> {

    List<ValidationInformation> prepareInsert(final T businessObject);

}
