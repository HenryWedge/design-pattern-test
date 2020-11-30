package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.demo.validation.ConstraintViolationException;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy2;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInserter;
import de.ppi.here.tcu.composite.inserter.Inserter;
import de.ppi.here.tcu.dao.FtpServerDao;
import de.ppi.here.tcu.entity.FtpServer;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.FtpServerValidator;


/**
 * Service zum Einfügen einer FtpServer-Entität in die Datenbank
 */
@Service
public class FtpServerAdministrationService3 implements AdministrationService<FtpServer> {

    @Autowired
    private FtpServerValidator ftpServerValidator;

    @Autowired
    private FtpServerDao ftpServerDao;

    @Autowired
    private ChangeRecordProtocolService<FtpServer> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private ChangeDataIterator<FtpServer> changeDataIterator;

    @Autowired
    private BasicInsertStrategy2<FtpServer> basicInsertStrategy;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpServer businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {
        Inserter<FtpServer> inserter =
            new BasicInserter<>(changeRecordProtocolService, administrationProtocolEventService, ftpServerDao,
                changeDataIterator, new FtpServer(), "FTPSERVER_MESSAGES_CREATED");
        return basicInsertStrategy.insert(businessObject, dialogUserIdInformation, ftpServerValidator,
            ftpServerDao, inserter);
    }
}
