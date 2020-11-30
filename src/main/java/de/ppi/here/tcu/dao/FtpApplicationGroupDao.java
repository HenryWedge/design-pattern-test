package de.ppi.here.tcu.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import de.ppi.here.tcu.entity.FtpApplicationGroup;

/**
 * Datenbankzugriffsobjekt für die FtpApplicationGroup-Entität
 */
@Repository
public class FtpApplicationGroupDao implements Dao<FtpApplicationGroup> {

    @Override
    public Optional<FtpApplicationGroup> findById(final Integer id) {
        return Optional.of( new FtpApplicationGroup() );
    }

    @Override
    public FtpApplicationGroup makePersistent(final FtpApplicationGroup entity) {
        return entity;
    }

    public FtpApplicationGroup findByGroupName(final String operatorId, final String mandatorId, final String groupName) {
        return new FtpApplicationGroup(operatorId, mandatorId, groupName);
    }
}
