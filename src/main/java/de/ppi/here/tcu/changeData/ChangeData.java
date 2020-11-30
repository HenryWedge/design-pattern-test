package de.ppi.here.tcu.changeData;

public class ChangeData {

    private final String fieldName;

    private final Object dataBefore;

    private final Object dataAfter;

    public ChangeData(final String fieldName, final Object dataBefore, final Object dataAfter) {
        this.fieldName = fieldName;
        this.dataBefore = dataBefore;
        this.dataAfter = dataAfter;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getDataBefore() {
        return dataBefore;
    }

    public Object getDataAfter() {
        return dataAfter;
    }
}
