package de.ppi.here.tcu.entity;

/**
 * Entitätsobjekt zu einer FtpApplication. Eine Ftp-Applikation ist eine Zuordnung von Ftp-Datei-Typen zu einer
 * Ftp-Gruppe
 */
public class FtpApplication implements Entity {

    private Integer id;

    private String operatorId;

    private String mandatorId;

    private String groupName;

    private String ftpFileType;

    private String name;

    private String orderDirection;

    private Integer ftpApplicationGroupId;

    public FtpApplication() {
    }


    public FtpApplication(final Integer id, final String operatorId, final String mandatorId,
        final String groupName, final String ftpFileType, final String name, final String orderDirection) {
        this.id = id;
        this.operatorId = operatorId;
        this.mandatorId = mandatorId;
        this.groupName = groupName;
        this.ftpFileType = ftpFileType;
        this.name = name;
        this.orderDirection = orderDirection;
    }


    @Override
    public Integer getId() {
        return null;
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


    public String getGroupName() {
        return groupName;
    }


    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }


    public String getFtpFileType() {
        return ftpFileType;
    }


    public void setFtpFileType(final String ftpFileType) {
        this.ftpFileType = ftpFileType;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public String getOrderDirection() {
        return orderDirection;
    }


    public void setOrderDirection(final String orderDirection) {
        this.orderDirection = orderDirection;
    }


    public Integer getFtpApplicationGroupId() {
        return ftpApplicationGroupId;
    }


    public void setFtpApplicationGroupId(final Integer ftpApplicationGroupId) {
        this.ftpApplicationGroupId = ftpApplicationGroupId;
    }
}
