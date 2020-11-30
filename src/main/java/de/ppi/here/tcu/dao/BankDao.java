package de.ppi.here.tcu.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import de.ppi.here.tcu.entity.Bank;

/**
 * Datenbankzugriffsobjekt für die Bank-Entität
 */
@Repository
public class BankDao implements Dao<Bank> {

    public Optional<Bank> findById(Integer id) {
           return Optional.of(new Bank());
    }

    public Bank makePersistent(final Bank entity) {
        return entity;
    }
}
