package de.ppi.here.demo.service.composition.preparation;

import de.ppi.here.demo.bo.BusinessObject;

public interface PreconditionCheckService<T extends BusinessObject> {

    void checkPrecondition(T businessObject);

}
