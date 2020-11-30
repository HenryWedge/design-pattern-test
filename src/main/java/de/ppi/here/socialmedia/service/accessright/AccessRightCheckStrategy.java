package de.ppi.here.socialmedia.service.accessright;

public interface AccessRightCheckStrategy {

    boolean checkAccessRights(Integer channelId, Integer userId);

}
