package de.ppi.here.tcu.changeData;

import java.util.List;
import de.ppi.here.tcu.entity.Entity;

/**
 * Fasst die Änderungen an einer Entität zusammen
 * @param <T> Typ der Entität
 */

public interface ChangeDataIterator<T extends Entity> {

    List<ChangeData> getDiffDataSet(final T original, final T changed);

}
