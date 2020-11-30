package de.ppi.here.tcu.service;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;

/**
 * Persitiert Zusatzfelder einer Entität in einer Datenbank
 * @param <T> Entität zu der die Zusatzfelder gehören
 */
public interface AddInfoDataPersistenceService<T extends Entity> {

    void persistAddInfoToBusinessObject(List<AddInfoField> addInfoFields, T businessObject);

}
