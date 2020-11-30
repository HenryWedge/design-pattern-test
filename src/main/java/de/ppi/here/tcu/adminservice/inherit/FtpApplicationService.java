package de.ppi.here.tcu.adminservice.inherit;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.CommonValidationContext;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.FtpApplicationDao;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplication;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.FtpApplicationValidator;


/**
 * Service zum Einfügen einer FtpApplicationGroup-Entität in die Datenbank
 */
@Service
public class FtpApplicationService extends AbstractInsertingMasterDataService<FtpApplication> {

    @Autowired
    private FtpApplicationValidator ftpApplicationValidator;

    @Autowired
    private FtpApplicationDao ftpApplicationDao;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private ChangeRecordProtocolService<FtpApplication> changeRecordProtocolService;

    @Autowired
    private FtpApplicationGroupDao ftpApplicationGroupDao;

    @Autowired
    private ChangeDataIterator<FtpApplication> changeDataIterator;

    @Override
    protected List<ValidationInformation> prepareForInsert(final FtpApplication businessObject) {
        return Collections.emptyList();
    }


    @Override
    protected MasterDataAdministrationOperationSuccessServiceResult internalInsert(
        final FtpApplication businessObject, final DialogUserIdInformation dialogUserIdInformation) {

        final FtpApplicationGroup group = ftpApplicationGroupDao.findByGroupName(businessObject.getOperatorId(),
            businessObject.getMandatorId(), businessObject.getGroupName());

        businessObject.setFtpApplicationGroupId(group.getId());

        final FtpApplication persistent = ftpApplicationDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList =
            changeDataIterator.getDiffDataSet(new FtpApplication(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            persistent, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult("FTPAPPLICATION_MESSAGES_CREATED");
    }


    @Override
    protected List<ValidationInformation> validateForInsert(final FtpApplication businessObject,
        final DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException {
        return ftpApplicationValidator.validate(businessObject, CommonValidationContext.createInsert());
    }


    @Override
    protected Optional<FtpApplication> getBeanFromDatabaseForCreation(final FtpApplication businessObject) {
        return ftpApplicationDao.findById(businessObject.getId());
    }


    @Override
    protected void checkInsertPreconditions(final FtpApplication businessObject) {
        // nichts tun
    }
}
