package de.ppi.here.tcu.dao;

import java.util.Optional;
import de.ppi.here.tcu.entity.FtpApplication;

/**
 * Datenbankzugriffsobjekt für die FtpApplication-Entität
 */
public class FtpApplicationDao implements Dao<FtpApplication> {

    @Override
    public Optional<FtpApplication> findById(final Integer id) {
        return Optional.of( new FtpApplication());
    }

    @Override
    public FtpApplication makePersistent(final FtpApplication businessObject) {
        return businessObject;
    }
}
