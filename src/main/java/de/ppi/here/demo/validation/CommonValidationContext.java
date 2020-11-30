package de.ppi.here.demo.validation;

public class CommonValidationContext implements ValidationContext {

    private String type;

    public CommonValidationContext(final String type) {
        this.type = type;
    }

    public static CommonValidationContext createInsert() {
        return new CommonValidationContext("Insert");
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
