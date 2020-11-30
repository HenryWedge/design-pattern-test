package de.ppi.here.tcu.dao;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(Integer id);

    T makePersistent(final T businessObject);

}
