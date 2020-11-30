package de.ppi.here.tcu.service;

import de.ppi.here.tcu.changeData.ChangeRecord;

/**
 * Sendet Protokoll-Events nachdem eine Einfüge-Operation erfolgreich abgeschlossen wurde.
 */
public interface AdministrationProtocolEventService {

    /**
     * Sendet ein Event, dass die Bearbeitung eines Objekts abeschlossen wurde
     * @param changeRecordBean
     */
    void createAndFireProtocolEvent(final ChangeRecord changeRecordBean);

}
