package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy;
import de.ppi.here.tcu.changeData.ChangeDataIteratorImpl;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInserter;
import de.ppi.here.tcu.composite.inserter.FtpApplicationInserter;
import de.ppi.here.tcu.dao.FtpApplicationDao;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplication;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.FtpApplicationValidator;


/**
 * Service zum Einfügen einer FtpApplication-Entität in die Datenbank
 */
@Service
public class FtpApplicationAdministrationService3 implements AdministrationService<FtpApplication> {

    @Autowired
    private ChangeRecordProtocolService<FtpApplication> changeDataProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private FtpApplicationGroupDao ftpApplicationGroupDao;

    @Autowired
    private FtpApplicationValidator ftpApplicationValidator;

    @Autowired
    private FtpApplicationDao ftpApplicationDao;

    @Autowired
    private BasicInsertStrategy<FtpApplication> basicInsertStrategy;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpApplication businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {
        BasicInserter<FtpApplication> basicInserter = new BasicInserter<>(changeDataProtocolService,
            administrationProtocolEventService, new FtpApplicationDao(), new ChangeDataIteratorImpl<>(),
            new FtpApplication(), "BANK_MESSAGES_CREATED");

        FtpApplicationInserter ftpInserter = new FtpApplicationInserter(basicInserter, ftpApplicationGroupDao);

        return basicInsertStrategy.insert(businessObject, dialogUserIdInformation, ftpApplicationValidator,
            ftpApplicationDao, ftpInserter);
    }
}
