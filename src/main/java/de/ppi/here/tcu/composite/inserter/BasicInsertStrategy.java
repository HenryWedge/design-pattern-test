package de.ppi.here.tcu.composite.inserter;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.Dao;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;

/**
 * Standard-Einfüge-Ablauf
 * @param <T>
 */
public class BasicInsertStrategy<T extends Entity> implements InsertStrategy<T> {

    private ChangeRecordProtocolService<T> changeRecordProtocolService;

    private AdministrationProtocolEventService administrationProtocolEventService;

    private Dao<T> dao;

    private ChangeDataIterator<T> changeDataIterator;

    private T originalObject;

    private String resultMessage;

    public BasicInsertStrategy(final ChangeRecordProtocolService<T> changeRecordProtocolService,
                               final AdministrationProtocolEventService administrationProtocolEventService, final Dao<T> dao,
                               final ChangeDataIterator<T> changeDataIterator, final T originalObject, final String resultMessage) {

        this.changeRecordProtocolService = changeRecordProtocolService;
        this.administrationProtocolEventService = administrationProtocolEventService;
        this.dao = dao;
        this.changeDataIterator = changeDataIterator;
        this.originalObject = originalObject;
        this.resultMessage = resultMessage;
    }


    public MasterDataAdministrationOperationSuccessServiceResult insert(T businessObject,
        DialogUserIdInformation dialogUserIdInformation) {

        final T persistent = dao.makePersistent(businessObject);

        final List<ChangeData> diffList = changeDataIterator.getDiffDataSet(originalObject, persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService
            .createAndPersistCreationChangeRecord(businessObject, diffList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult(resultMessage);
    }
}
