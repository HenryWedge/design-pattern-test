package de.ppi.here.tcu.adminservice.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.adminservice.AdministrationService;
import de.ppi.here.tcu.adminservice.composite.strategy.BasicInsertStrategy;
import de.ppi.here.tcu.changeData.ChangeDataIterator;
import de.ppi.here.tcu.changeData.ChangeRecordProtocolService;
import de.ppi.here.tcu.composite.inserter.BasicInserter;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;
import de.ppi.here.tcu.service.AdministrationProtocolEventService;
import de.ppi.here.tcu.validation.ConstraintViolationException;
import de.ppi.here.tcu.validation.FtpApplicationGroupValidator;


/**
 * Service zum Einfügen einer FtpApplicationGroup-Entität in die Datenbank
 */
@Service
public class FtpApplicationGroupAdministrationService3 implements AdministrationService<FtpApplicationGroup> {

    @Autowired
    private FtpApplicationGroupValidator validator;

    @Autowired
    private FtpApplicationGroupDao ftpApplicationGroupDao;

    @Autowired
    private ChangeRecordProtocolService<FtpApplicationGroup> changeRecordProtocolService;

    @Autowired
    private AdministrationProtocolEventService administrationProtocolEventService;

    @Autowired
    private FtpApplicationGroupDao dao;

    @Autowired
    private ChangeDataIterator<FtpApplicationGroup> changeDataIterator;

    @Autowired
    private BasicInsertStrategy<FtpApplicationGroup> basicInsertStrategy;

    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(final FtpApplicationGroup businessObject,
        final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException, ConstraintViolationException {
        final BasicInserter<FtpApplicationGroup> inserter =
            new BasicInserter<>(changeRecordProtocolService, administrationProtocolEventService, dao,
                changeDataIterator, new FtpApplicationGroup(), "FTPAPPLICATIONGROUP_MESSAGES_CREATED");
        return basicInsertStrategy.insert(businessObject, dialogUserIdInformation, validator,
            ftpApplicationGroupDao, inserter);
    }
}
