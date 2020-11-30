package de.ppi.here.demo.service.composition.validation;

import java.util.ArrayList;
import java.util.List;
import de.ppi.here.demo.service.util.ValidationInformation;

public class BicValidator {

    private static final int BIC_LENGTH = 8;

    List<ValidationInformation> isValid(BicValidatable businessObject) {
        List<ValidationInformation> validationInformations = new ArrayList<>();
        String bic = businessObject.getBic();
        if (bic.length() != BIC_LENGTH) {
            validationInformations.add(new ValidationInformation("Ungültige Länge der BIC"));
        }

        return validationInformations;
    }
}
