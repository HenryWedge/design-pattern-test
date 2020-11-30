package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.ChannelAccessRights;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.service.FriendStatusUpdateService;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.notification.PushNotificationService;

@Service
public class PrivateChatBo extends AbstractPrivateChannelBo {

    @Autowired
    private FriendStatusUpdateService friendStatusUpdateService;

    @Override
    protected void updateFriendStatus(int channelId) {
        friendStatusUpdateService.increaseFriendStatus(5, channelId);
    }

    @Override
    protected NotificationService getNotificationService() {
        return new PushNotificationService();
    }

}
