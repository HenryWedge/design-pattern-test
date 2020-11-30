package de.ppi.here.demo.validation;

public class MandatorValidationContext implements ValidationContext {

    private String type;

    public MandatorValidationContext(final String type) {
        this.type = type;
    }

    public static MandatorValidationContext createInsert() {
        return new MandatorValidationContext("Insert");
    }

    @Override
    public FieldlabelMessageGroup getPropertyContext() {
        return null;
    }

    @Override
    public void setDefaultPropertyContext(final FieldlabelMessageGroup defaultPropertyContext) {
    }

    @Override
    public FieldlabelMessageGroup getDefaultPropertyContext() {
        return null;
    }
}
