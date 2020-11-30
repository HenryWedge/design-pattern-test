package de.ppi.here.tcu.changeData;

import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Entity;
import de.ppi.here.tcu.result.DialogUserIdInformation;


/**
 * Service, der die Änderungen an einem Objekt in die Datenbank schreibt
 *
 * @param <T>
 */
public interface ChangeRecordProtocolService<T extends Entity> {
    ChangeRecord createAndPersistCreationChangeRecord(final T businessObject,
        final List<ChangeData> diffWrapperList, final DialogUserIdInformation dialogUserIdInformation,
        final Object o, final boolean b);
}
