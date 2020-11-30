package de.ppi.here.demo.service.composition.validation;

import java.util.List;
import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.service.util.ValidationInformation;

public interface ValidationService<T extends BusinessObject> {

    List<ValidationInformation> validate(T businessObject);

}
