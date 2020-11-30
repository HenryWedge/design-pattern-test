package de.ppi.here.tcu.dao;

import java.util.Optional;
import de.ppi.here.tcu.entity.Bank;

/**
 * Datenbankzugriffsobjekt für die Bank-Entität
 */
public class BankDao implements Dao<Bank> {

    public Optional<Bank> findById(Integer id) {
           return Optional.of(new Bank());
    }

    public Bank makePersistent(final Bank businessObject) {
        return businessObject;
    }
}
