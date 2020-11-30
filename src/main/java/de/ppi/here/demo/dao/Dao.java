package de.ppi.here.demo.dao;

import de.ppi.here.demo.bo.BusinessObject;

public interface Dao<T extends BusinessObject> {

    void persistObject(T businessObject);
}
