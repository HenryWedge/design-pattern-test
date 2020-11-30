package de.ppi.here.demo.service.composition.persistence;

import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.dao.Dao;

public class SimplePersistenceService<T extends BusinessObject> implements PersistenceService<T> {

    @Override
    public void persistObject(final Dao<T> dao, final T businessObject) {
        dao.persistObject(businessObject);
    }

}
