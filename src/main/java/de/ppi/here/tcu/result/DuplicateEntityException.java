package de.ppi.here.tcu.result;

import de.ppi.here.tcu.entity.Entity;

/**
 * Exception, die geworfen wird, wenn eine Entität bereits in der Datenbank vorhanden ist
 */
public class DuplicateEntityException extends Exception {

    private Entity entity;

    public DuplicateEntityException(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
