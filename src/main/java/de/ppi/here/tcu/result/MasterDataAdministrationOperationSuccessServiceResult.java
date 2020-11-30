package de.ppi.here.tcu.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Erfolgsmeldung einer Einfüge-Operation
 */
public class MasterDataAdministrationOperationSuccessServiceResult {

    private List<ValidationInformation> validationInformations = new ArrayList<>();
    private String message;

    public void addSubServiceResult(final ValidationInformation information) {
        validationInformations.add(information);
    }

    public MasterDataAdministrationOperationSuccessServiceResult(String message) {
        this.message = message;
    }
}
