package de.ppi.here.tcu.service;

import de.ppi.here.tcu.entity.Entity;

/**
 * Zusatzfeld, welches einer Entität zugeordnet werden kann
 */
public class AddInfoField implements Entity {

    private Integer id;

    private String field;

    private String data;

    public AddInfoField() {
    }

    public AddInfoField(final Integer id, final String field, final String data) {
        this.id = id;
        this.field = field;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public String getField() {
        return field;
    }

    public String getData() {
        return data;
    }
}
