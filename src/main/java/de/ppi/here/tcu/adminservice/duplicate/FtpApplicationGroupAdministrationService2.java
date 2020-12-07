package de.ppi.here.tcu.adminservice.duplicate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
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
public class FtpApplicationGroupAdministrationService2 implements AdministrationService<FtpApplicationGroup> {

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
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpApplicationGroup businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            ftpApplicationGroupValidator.validate(businessObject, ValidationContext.createInsert());

        if(ftpApplicationGroupDao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final FtpApplicationGroup persistent = ftpApplicationGroupDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList =
            changeDataIterator.getDiffDataSet(new FtpApplicationGroup(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            businessObject, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("FTPAPPLICATIONGROUP_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }
}
