package de.ppi.here.tcu.composite.prepare;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.ValidationInformation;

/**
 * Service, der ein Objekt bevor es in die Datenbank geschrieben wird nach bestimmten Kriterien bearbeitet
 * @param <T>
 */
public interface InsertPreparationService<T extends Entity> {

    List<ValidationInformation> prepareInsert(T BusinessObject);

}
