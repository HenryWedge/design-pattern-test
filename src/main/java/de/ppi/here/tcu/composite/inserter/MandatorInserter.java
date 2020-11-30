package de.ppi.here.tcu.composite.inserter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.MandatorDao;
import de.ppi.here.tcu.entity.Mandator;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AddInfoDataPersistenceService;
import de.ppi.here.tcu.service.AddInfoField;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.service.MandatorInfrastructureService;

/**
 * Einfüge-Ablauf für einen Mandaten
 */
@Service
public class MandatorInserter implements Inserter<Mandator> {

    @Autowired
    private MandatorDao mandatorDao;

    @Autowired
    private ChangeDataIterator<Mandator> changeDataIterator;

    @Autowired
    private ChangeDataIterator<AddInfoField> addInfoChangeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<Mandator> otherChangeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private AddInfoDataPersistenceService<Mandator> addInfoDataPersistenceService;

    @Autowired
    private MandatorInfrastructureService mandatorInfrastructureService;

    public MasterDataAdministrationOperationSuccessServiceResult insert(Mandator businessObject,
        DialogUserIdInformation dialogUserIdInformation) {

        final Mandator persistent = mandatorDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new Mandator(), persistent);

        if (!businessObject.getAddInfoFieldList().isEmpty()) {
            diffWrapperList.addAll(businessObject.getAddInfoFieldList().stream()
                .map(field -> addInfoChangeDataIterator.getDiffDataSet(field, new AddInfoField()))
                .flatMap(Collection::stream).collect(Collectors.toList()));
        }

        final ChangeRecord changeRecordBean =
            otherChangeRecordProtocolService.createAndPersistCreationChangeRecord(persistent, diffWrapperList,
                dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        addInfoDataPersistenceService.persistAddInfoToBusinessObject(businessObject.getAddInfoFieldList(),
            businessObject);

        mandatorInfrastructureService.createMandatorInfrastructureWithOrderTypeInheritance(businessObject,
            changeRecordBean.getId());

        return new MasterDataAdministrationOperationSuccessServiceResult("MANDATOR_MESSAGES_CREATED");

    }
}
