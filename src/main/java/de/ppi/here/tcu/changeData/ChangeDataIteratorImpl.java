package de.ppi.here.tcu.changeData;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import de.ppi.here.tcu.entity.Entity;

/**
 * Mock-Implementierung des {@link ChangeDataIterator}
 * @param <T>
 */
@Service
public class ChangeDataIteratorImpl<T extends Entity> implements ChangeDataIterator<T> {

    @Override
    public List<ChangeData> getDiffDataSet(final T original, final T changed) {
        return Collections.emptyList();
    }
}
