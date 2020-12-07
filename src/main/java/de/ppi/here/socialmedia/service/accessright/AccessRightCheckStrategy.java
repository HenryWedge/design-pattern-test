package de.ppi.here.socialmedia.service.accessright;

public interface AccessRightCheckStrategy {

    boolean hasAccessRights(Integer channelId, Integer userId);

}
