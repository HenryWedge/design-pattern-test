package de.ppi.here.tcu.adminservice.duplicate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.FtpApplicationDao;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplication;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.result.ValidationInformation;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.FtpApplicationValidator;
import de.ppi.here.tcu.validation.ValidationContext;


/**
 * Service zum Einfügen einer FtpApplication-Entität in die Datenbank
 */
@Service
public class FtpApplicationAdministrationService2 implements AdministrationService<FtpApplication> {

    @Autowired
    private FtpApplicationValidator ftpApplicationValidator;

    @Autowired
    private FtpApplicationDao ftpApplicationDao;

    @Autowired
    private FtpApplicationGroupDao ftpApplicationGroupDao;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private ChangeDataIterator<FtpApplication> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<FtpApplication> changeRecordProtocolService;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpApplication businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            ftpApplicationValidator.validate(businessObject, ValidationContext.createInsert());

        if (ftpApplicationDao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final FtpApplicationGroup group = ftpApplicationGroupDao.findByGroupName(businessObject.getOperatorId(),
            businessObject.getMandatorId(), businessObject.getGroupName());

        businessObject.setFtpApplicationGroupId(group.getId());

        final FtpApplication persistent = ftpApplicationDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList =
            changeDataIterator.getDiffDataSet(new FtpApplication(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            persistent, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("FTPAPPLICATION_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }
}
