package de.ppi.here.tcu.adminservice.inherit;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.PreconditionNotFulfilledException;
import de.ppi.here.tcu.dao.FtpServerDao;
import de.ppi.here.tcu.entity.FtpServer;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.FtpServerValidator;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer FtpServer-Entität in die Datenbank
 */
@Service
public class FtpServerAdministrationService extends AbstractInsertingMasterDataService<FtpServer> {

    @Autowired
    private FtpServerValidator ftpServerValidator;

    @Autowired
    private FtpServerDao ftpServerDao;

    @Autowired
    private ChangeDataIterator<FtpServer> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<FtpServer> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Override
    protected void checkInsertPreconditions(final FtpServer businessObject)
        throws PreconditionNotFulfilledException {
        // Nichts zu prüfen.
    }


    @Override
    protected List<ValidationInformation> validateForInsert(final FtpServer businessObject,
        final DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException {
        return ftpServerValidator.validate(businessObject, ValidationContext.createInsert());
    }


    @Override
    protected List<ValidationInformation> prepareForInsert(final FtpServer businessObject) {
        return Collections.emptyList();
    }


    @Override
    protected MasterDataAdministrationOperationSuccessServiceResult internalInsert(final FtpServer businessObject,
        final DialogUserIdInformation dialogUserIdInformation) {

        final FtpServer persistent = ftpServerDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new FtpServer(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            businessObject, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult("FTPSERVER_MESSAGES_CREATED");
    }


    @Override
    protected Optional<FtpServer> getBeanFromDatabaseForCreation(final FtpServer businessObject) {
        return ftpServerDao.findById(businessObject.getId());
    }

}
