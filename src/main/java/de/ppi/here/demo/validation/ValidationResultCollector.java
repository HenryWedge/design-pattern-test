package de.ppi.here.demo.validation;

public interface ValidationResultCollector {

    void checkConstraintViolations();
    void add(SingleFieldConstraintViolation violation);
}
