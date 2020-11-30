package de.ppi.here.tcu.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import de.ppi.here.tcu.entity.FtpServer;

/**
 * Datenbankzugriffsobjekt für die FtpGroup-Entität
 */
@Repository
public class FtpServerDao implements Dao<FtpServer> {

    @Override
    public Optional<FtpServer> findById(final Integer id) {
        return Optional.of(new FtpServer());
    }

    @Override
    public FtpServer makePersistent(final FtpServer entity) {
        return entity;
    }
}
