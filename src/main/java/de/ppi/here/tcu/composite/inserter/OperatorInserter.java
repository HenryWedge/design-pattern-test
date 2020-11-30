package de.ppi.here.tcu.composite.inserter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import de.ppi.here.tcu.entity.Operator;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.OperatorDao;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.OperatorInfrastructureService;

/**
 * Einfüge-Ablauf für einen Betreiber
 */
public class OperatorInserter implements Inserter<Operator> {

    @Autowired
    private OperatorDao operatorDao;

    @Autowired
    private ChangeDataIterator<Operator> changeDataIterator;

    @Autowired
    private OperatorInfrastructureService operatorInfrastructureService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private ChangeRecordProtocolService<Operator> changeRecordProtocolService;

    public MasterDataAdministrationOperationSuccessServiceResult insert(Operator businessObject,
        DialogUserIdInformation dialogUserIdInformation) {

        final Operator persistent = operatorDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new Operator(), persistent);

        final ChangeRecord changeRecordBean =
            changeRecordProtocolService.createAndPersistCreationChangeRecord(businessObject, diffWrapperList,
                dialogUserIdInformation, null, false);

        operatorInfrastructureService.createOperatorInfrastructure(businessObject, businessObject.getId(),
            dialogUserIdInformation);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult("OPERATOR_MESSAGES_CREATED");
    }

}
