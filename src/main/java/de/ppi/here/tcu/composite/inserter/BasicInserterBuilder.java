package de.ppi.here.tcu.composite.inserter;

import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.Dao;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;


public class BasicInserterBuilder<T extends Entity> {

    private ChangeRecordProtocolService<T> changeRecordProtocolService;

    private AdministrationProtocolEventService administrationProtocolEventService;

    private Dao<T> dao;

    private ChangeDataIterator<T> changeDataIterator;

    private T originalObject;

    private String resultMessage;

    public BasicInserterBuilder<T> addChangeRecordProtocolService(
        ChangeRecordProtocolService<T> changeRecordProtocolService) {
        this.changeRecordProtocolService = changeRecordProtocolService;
        return this;
    }


    public BasicInserterBuilder<T> addAdministrationProtocolEventService(
        AdministrationProtocolEventService administrationProtocolEventService) {
        this.administrationProtocolEventService = administrationProtocolEventService;
        return this;
    }


    public BasicInserterBuilder<T> addDao(Dao<T> dao) {
        this.dao = dao;
        return this;
    }


    public BasicInserterBuilder<T> addChangeDataIterator(ChangeDataIterator<T> changeDataIterator) {
        this.changeDataIterator = changeDataIterator;
        return this;
    }


    public BasicInserterBuilder<T> addOriginalObject(T originalObject) {
        this.originalObject = originalObject;
        return this;
    }


    public BasicInserterBuilder<T> addResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
        return this;
    }


    public BasicInserter<T> build() {
        return new BasicInserter<>(changeRecordProtocolService, administrationProtocolEventService, dao,
            changeDataIterator, originalObject, resultMessage);
    }

}
