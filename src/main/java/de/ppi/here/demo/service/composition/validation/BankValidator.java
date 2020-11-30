package de.ppi.here.demo.service.composition.validation;

import java.util.ArrayList;
import java.util.List;
import de.ppi.here.demo.bo.Bank;
import de.ppi.here.demo.service.util.ValidationInformation;

public class BankValidator implements ValidationService<Bank> {

    private BicValidator bicValidator;

    @Override
    public List<ValidationInformation> validate(final Bank businessObject) {
        final List<ValidationInformation> validationInformations = new ArrayList<>();
        bicValidator.isValid(businessObject);
        return null;
    }
}
