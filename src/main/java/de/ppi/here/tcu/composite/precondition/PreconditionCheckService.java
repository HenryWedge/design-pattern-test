package de.ppi.here.tcu.composite.precondition;

import java.util.List;
import de.ppi.here.tcu.result.ValidationInformation;

/**
 * Service, der die Vorbedingungen für das Einfügen einer Entität in die Datenbank prüft
 * @param <T>
 */
public interface PreconditionCheckService<T> {

    List<ValidationInformation> checkPrecondition(T businessObject) throws PreconditionNotFulfilledException;

}
