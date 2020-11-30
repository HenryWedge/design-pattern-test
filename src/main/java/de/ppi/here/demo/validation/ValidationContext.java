package de.ppi.here.demo.validation;

public interface ValidationContext {
    FieldlabelMessageGroup getPropertyContext();

    void setDefaultPropertyContext(FieldlabelMessageGroup defaultPropertyContext);

    FieldlabelMessageGroup getDefaultPropertyContext();
}
