package de.ppi.here.tcu.composite.inserter;

import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.Dao;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;


public class InsertStrategyBuilder<T extends Entity> {

    private ChangeRecordProtocolService<T> changeRecordProtocolService;

    private AdministrationProtocolEventService administrationProtocolEventService;

    private Dao<T> dao;

    private ChangeDataIterator<T> changeDataIterator;

    private T originalObject;

    private String resultMessage;

    public InsertStrategyBuilder<T> addChangeRecordProtocolService(
        ChangeRecordProtocolService<T> changeRecordProtocolService) {
        this.changeRecordProtocolService = changeRecordProtocolService;
        return this;
    }


    public InsertStrategyBuilder<T> addAdministrationProtocolEventService(
        AdministrationProtocolEventService administrationProtocolEventService) {
        this.administrationProtocolEventService = administrationProtocolEventService;
        return this;
    }


    public InsertStrategyBuilder<T> addDao(Dao<T> dao) {
        this.dao = dao;
        return this;
    }


    public InsertStrategyBuilder<T> addChangeDataIterator(ChangeDataIterator<T> changeDataIterator) {
        this.changeDataIterator = changeDataIterator;
        return this;
    }


    public InsertStrategyBuilder<T> addOriginalObject(T originalObject) {
        this.originalObject = originalObject;
        return this;
    }


    public InsertStrategyBuilder<T> addResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }


    public BasicInsertStrategy<T> build() {
        return new BasicInsertStrategy<>(changeRecordProtocolService, administrationProtocolEventService, dao,
            changeDataIterator, originalObject, resultMessage);
    }

}
