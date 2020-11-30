package de.ppi.here.tcu.dao;

import java.util.Optional;
import de.ppi.here.tcu.entity.FtpApplicationGroup;

/**
 * Datenbankzugriffsobjekt für die FtpApplicationGroup-Entität
 */
public class FtpApplicationGroupDao implements Dao<FtpApplicationGroup> {

    @Override
    public Optional<FtpApplicationGroup> findById(final Integer id) {
        return Optional.of( new FtpApplicationGroup() );
    }

    @Override
    public FtpApplicationGroup makePersistent(final FtpApplicationGroup businessObject) {
        return null;
    }

    public FtpApplicationGroup findByGroupName(final String operatorId, final String mandatorId, final String groupName) {
        return new FtpApplicationGroup(operatorId, mandatorId, groupName);
    }
}
