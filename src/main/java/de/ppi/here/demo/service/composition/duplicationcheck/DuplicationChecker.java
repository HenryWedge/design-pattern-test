package de.ppi.here.demo.service.composition.duplicationcheck;

import java.util.List;
import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.service.util.ValidationInformation;

public interface DuplicationChecker<T extends BusinessObject> {

    List<ValidationInformation> checkDuplicate(T businessObject);

}
