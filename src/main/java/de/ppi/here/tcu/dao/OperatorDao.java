package de.ppi.here.tcu.dao;

import java.util.Optional;
import de.ppi.here.tcu.entity.Operator;

/**
 * Datenbankzugriffsobjekt für die FtpApplication-Entität
 */
public class OperatorDao implements Dao<Operator> {

    @Override
    public Optional<Operator> findById(final Integer id) {
        return Optional.of(new Operator());
    }

    @Override
    public Operator makePersistent(final Operator businessObject) {
        return businessObject;
    }
}
