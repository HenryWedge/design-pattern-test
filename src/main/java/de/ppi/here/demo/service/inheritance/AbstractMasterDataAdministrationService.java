package de.ppi.here.demo.service.inheritance;

import de.ppi.here.demo.bo.BusinessObject;
import de.ppi.here.demo.dao.Dao;
import de.ppi.here.demo.service.MasterDataAdministrationService;

public abstract class AbstractMasterDataAdministrationService<T extends BusinessObject> implements MasterDataAdministrationService<T> {

    protected void persistObject(T businessObject) {
        getDao().persistObject(businessObject);
    }

    protected abstract Dao<T> getDao();
}
