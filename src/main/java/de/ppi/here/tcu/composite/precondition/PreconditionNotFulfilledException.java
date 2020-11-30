package de.ppi.here.tcu.composite.precondition;

public class PreconditionNotFulfilledException extends Exception {

    public PreconditionNotFulfilledException(final String message) {
        super(message);
    }
}
