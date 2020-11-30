package de.ppi.here.tcu.changeData;

/**
 * Repräsentiert einen Änderungsdatensatz
 */
public class ChangeRecord {

    private Integer id;

    public ChangeRecord(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
