package de.ppi.here.tcu.entity;

/**
 * Entitätsobjekt einer Bank
 */
public class Bank implements Entity {

    private Integer id;

    private String operatorId;

    private String mandatorId;

    private String isoCountryCode;

    private String bankCode;

    private String bic;

    public Bank() {
    }


    public Bank(final Integer id, final String operatorId, final String mandatorId, final String isoCountryCode,
        final String bankCode, final String bic) {
        this.id = id;
        this.operatorId = operatorId;
        this.mandatorId = mandatorId;
        this.isoCountryCode = isoCountryCode;
        this.bankCode = bankCode;
        this.bic = bic;
    }


    public Integer getId() {
        return id;
    }


    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(final String operatorId) {
        this.operatorId = operatorId;
    }

    public String getMandatorId() {
        return mandatorId;
    }

    public void setMandatorId(final String mandatorId) {
        this.mandatorId = mandatorId;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(final String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(final String bic) {
        this.bic = bic;
    }
}
