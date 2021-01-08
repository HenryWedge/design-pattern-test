package de.ppi.here.tcu.composite.inserter;

import de.ppi.here.tcu.entity.FtpApplication;
import de.ppi.here.tcu.entity.FtpApplicationGroup;
import de.ppi.here.tcu.dao.FtpApplicationGroupDao;
import de.ppi.here.tcu.result.DialogUserIdInformation;
import de.ppi.here.tcu.result.DuplicateEntityException;
import de.ppi.here.tcu.result.MasterDataAdministrationOperationSuccessServiceResult;

/**
 * Einfügeablauf für eine Ftp-Applikation
 */
public class FtpApplicationInsertStrategy implements InsertStrategy<FtpApplication> {

    private BasicInsertStrategy<FtpApplication> basicInserter;

    private FtpApplicationGroupDao ftpApplicationGroupDao;

    public FtpApplicationInsertStrategy(final BasicInsertStrategy<FtpApplication> basicInserter,
                                        final FtpApplicationGroupDao ftpApplicationGroupDao) {
        this.basicInserter = basicInserter;
        this.ftpApplicationGroupDao = ftpApplicationGroupDao;
    }


    @Override
    public MasterDataAdministrationOperationSuccessServiceResult insert(
            final FtpApplication businessObject, final DialogUserIdInformation dialogUserIdInformation)
        throws DuplicateEntityException {

        final FtpApplicationGroup group = ftpApplicationGroupDao.findByGroupName(businessObject.getOperatorId(),
            businessObject.getMandatorId(), businessObject.getGroupName());

        businessObject.setFtpApplicationGroupId(group.getId());

        return basicInserter.insert(businessObject, dialogUserIdInformation);
    }
}
