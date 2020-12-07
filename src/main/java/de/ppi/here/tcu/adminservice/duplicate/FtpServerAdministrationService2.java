package de.ppi.here.tcu.adminservice.duplicate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.changeData.ChangeData;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecord;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.dao.FtpServerDao;
import de.ppi.here.tcu.entity.FtpServer;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
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
public class FtpServerAdministrationService2 implements AdministrationService<FtpServer> {

    @Autowired
    private FtpServerValidator validator;

    @Autowired
    private FtpServerDao ftpServerDao;

    @Autowired
    private ChangeDataIterator<FtpServer> changeDataIterator;

    @Autowired
    private ChangeRecordProtocolService<FtpServer> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpServer businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final List<ValidationInformation> validationInformations =
            validator.validate(businessObject, ValidationContext.createInsert());

        if(ftpServerDao.findById(businessObject.getId()).isPresent()) {
            throw new DuplicateEntityException(businessObject);
        }

        final FtpServer persistent = ftpServerDao.makePersistent(businessObject);

        final List<ChangeData> diffWrapperList = changeDataIterator.getDiffDataSet(new FtpServer(), persistent);

        final ChangeRecord changeRecordBean = changeRecordProtocolService.createAndPersistCreationChangeRecord(
            businessObject, diffWrapperList, dialogUserIdInformation, null, false);

        administrationProtocolEventService.createAndFireProtocolEvent(changeRecordBean);

        final MasterDataAdministrationOperationSuccessServiceResult serviceResult =
            new MasterDataAdministrationOperationSuccessServiceResult("FTPSERVER_MESSAGES_CREATED");

        for (final ValidationInformation information : validationInformations) {
            serviceResult.addSubServiceResult(information);
        }

        return serviceResult;
    }
}
