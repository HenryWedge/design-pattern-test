package de.ppi.here.tcu.entity;

public class FtpFileType implements Entity {

    private String operatorId;

    private String mandatorId;

    private String orderDirection;

    private String ftpFileType;

    private Integer techId;

    public FtpFileType() {
    }

    public FtpFileType(final String operatorId, final String mandatorId, final String orderDirection,
                       final String ftpFileType, final Integer techId) {
        this.operatorId = operatorId;
        this.mandatorId = mandatorId;
        this.orderDirection = orderDirection;
        this.ftpFileType = ftpFileType;
        this.techId = techId;
    }


    @Override
    public Integer getId() {
        return techId;
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

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(final String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getFtpFileType() {
        return ftpFileType;
    }

    public void setFtpFileType(final String ftpFileType) {
        this.ftpFileType = ftpFileType;
    }
}
