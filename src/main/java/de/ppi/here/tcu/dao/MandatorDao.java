package de.ppi.here.tcu.dao;

import java.util.Optional;
import de.ppi.here.tcu.entity.Mandator;

/**
 * Datenbankzugriffsobjekt für die Mandator-Entität
 */
public class MandatorDao implements Dao<Mandator> {

    @Override
    public Optional<Mandator> findById(final Integer id) {
        return Optional.empty();
    }

    @Override
    public Mandator makePersistent(final Mandator businessObject) {
        return null;
    }
}
