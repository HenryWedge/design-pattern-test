package de.ppi.here.tcu.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import de.ppi.here.tcu.entity.Operator;

/**
 * Datenbankzugriffsobjekt für die FtpApplication-Entität
 */
@Repository
public class OperatorDao implements Dao<Operator> {

    @Override
    public Optional<Operator> findById(final Integer id) {
        return Optional.of(new Operator());
    }

    @Override
    public Operator makePersistent(final Operator entity) {
        return entity;
    }
}
