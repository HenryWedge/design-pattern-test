package de.ppi.here.demo.service.composition.changedata;

import de.ppi.here.demo.bo.BusinessObject;

public interface ChangeDataPersister<T extends BusinessObject> {

    void protocolChangeData(T businessObject);

}
