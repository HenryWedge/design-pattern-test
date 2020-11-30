package de.ppi.here.demo.service.composition.persistence;

import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.dao.Dao;

public interface PersistenceService<T extends BusinessObject> {

    void persistObject(Dao<T> dao, T businessObject);
}
