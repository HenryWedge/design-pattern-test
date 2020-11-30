package de.ppi.here.demo.bo;

import de.ppi.here.demo.service.composition.validation.BicValidatable;

public class Bank implements BusinessObject, BicValidatable {

    private String operatorId;
    private String mandatorId;
    private String isoCountryCode;
    private String bankCode;
    private String bic;

    @Override
    public String getBic() {
        return bic;
    }
}
