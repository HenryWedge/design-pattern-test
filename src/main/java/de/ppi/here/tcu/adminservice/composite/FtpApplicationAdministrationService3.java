package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.InsertContext;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInsertStrategy;
import de.ppi.here.tcu.composite.inserter.InsertStrategyBuilder;
import de.ppi.here.tcu.composite.inserter.FtpApplicationInsertStrategy;
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
    private ChangeDataIterator<FtpApplication> changeDataIterator;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpApplication businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {

        final BasicInsertStrategy<FtpApplication> inserter = new InsertStrategyBuilder<FtpApplication>()
            .addAdministrationProtocolEventService(administrationProtocolEventService)
            .addChangeRecordProtocolService(changeDataProtocolService)
            .addChangeDataIterator(changeDataIterator)
            .addDao(ftpApplicationDao)
            .addOriginalObject(new FtpApplication())
            .addResultMessage("FTPAPPLICATION_MESSAGES_CREATED")
            .build();

        final FtpApplicationInsertStrategy ftpInserter = new FtpApplicationInsertStrategy(inserter, ftpApplicationGroupDao);

        return new InsertContext<>(ftpApplicationValidator, ftpApplicationDao, ftpInserter).insert(
            businessObject, dialogUserIdInformation);
    }
}
