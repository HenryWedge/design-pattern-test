package de.ppi.here.tcu.entity;

/**
 * Entitätsobjekt eines FtpServers
 */
public class FtpServer implements Entity {

    private String operatorId;

    private String serverId;

    private Integer techId;

    private String hostName;

    private Integer port;

    public FtpServer() {
    }


    public FtpServer(final String operatorId, final String serverId, final Integer techId, final String hostName,
        final Integer port) {
        this.operatorId = operatorId;
        this.serverId = serverId;
        this.techId = techId;
        this.hostName = hostName;
        this.port = port;
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


    public String getServerId() {
        return serverId;
    }


    public void setServerId(final String serverId) {
        this.serverId = serverId;
    }


    public String getHostName() {
        return hostName;
    }


    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }


    public Integer getPort() {
        return port;
    }


    public void setPort(final Integer port) {
        this.port = port;
    }
}
