package de.ppi.here.tcu.validation;

public class ValidationContext {

    String type;

    String context;

    private ValidationContext(final String type, final String context) {
        this.type = type;
        this.context = context;
    }


    public static ValidationContext createInsert() {
        return new ValidationContext("common", "insert");
    }


    public static ValidationContext createMandatorInsert() {
        return new ValidationContext("mandator", "insert");
    }
}
