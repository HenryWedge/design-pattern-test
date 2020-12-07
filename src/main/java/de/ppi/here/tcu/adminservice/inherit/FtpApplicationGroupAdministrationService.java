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
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.FtpApplicationGroupValidator;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer FtpApplicationGroup-Entität in die Datenbank
 */
@Service
public class FtpApplicationGroupAdministrationService
    extends AbstractInsertingMasterDataService<FtpApplicationGroup> {

    @Autowired
    private FtpApplicationGroupValidator ftpApplicationGroupValidator;

    @Autowired
    private FtpApplicationGroupDao ftpApplicationGroupDao;

    @Autowired
    private ChangeDataIterator<FtpApplicationGroup> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<FtpApplicationGroup> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Override
    protected void checkInsertPreconditions(final FtpApplicationGroup businessObject)
        throws PreconditionNotFulfilledException {
        // keine Vorbedingungen
    }


    @Override
    protected Optional<FtpApplicationGroup> getBeanFromDatabaseForCreation(
        final FtpApplicationGroup businessObject) {
        return ftpApplicationGroupDao.findById(businessObject.getId());
    }


    @Override
    protected List<ValidationInformation> prepareForInsert(final FtpApplicationGroup businessObject) {
        return Collections.emptyList();
    }


    @Override
    protected MasterDataAdministrationOperationSuccessServiceResult internalInsert(
        final FtpApplicationGroup businessObject, final DialogUserIdInformation dialogUserIdInformation) {

        final FtpApplicationGroup persistent = ftpApplicationGroupDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList =
            changeDataIterator.getDiffDataSet(new FtpApplicationGroup(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            businessObject, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        return new MasterDataAdministrationOperationSuccessServiceResult("FTPAPPLICATIONGROUP_MESSAGES_CREATED");
    }


    @Override
    protected List<ValidationInformation> validateForInsert(final FtpApplicationGroup businessObject,
        final DialogUserIdInformation dialogUserIdInformation) throws ConstraintViolationException {
        return ftpApplicationGroupValidator.validate(businessObject, ValidationContext.createInsert());
    }

}
