package de.ppi.here.tcu.dao;

import java.util.Optional;

public interface Dao<T> {

    /**
     * Findet eine Entität anhand seiner Id
     * @param id Id des Objektes
     * @return Entität
     */
    Optional<T> findById(Integer id);

    /**
     * Persistiert eine Entität in der Datenbank
     * @param entity Einzufügende Entität
     * @return Eingefügte Entität
     */
    T makePersistent(final T entity);

}
