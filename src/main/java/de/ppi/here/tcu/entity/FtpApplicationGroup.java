package de.ppi.here.tcu.entity;

/**
 * Entitätsobjekt einer Gruppe von Ftp-Applikationen die einem Ftp-Zugang zugeordnet werden
 */
public class FtpApplicationGroup implements Entity {

    private Integer id;

    private String operatorId;

    private String mandatorId;

    private String name;

    public FtpApplicationGroup() {
    }


    public FtpApplicationGroup(final String operatorId, final String mandatorId, final String name) {
        this.operatorId = operatorId;
        this.mandatorId = mandatorId;
        this.name = name;
    }


    @Override
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
