package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.ChannelAccessRights;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;

@Service
public class PrivateGroupChannelBo extends AbstractPrivateChannelBo {

    public PrivateGroupChannelBo(final PostDao postDao,
        final ChannelAccessRights channelAccessRights, Router router) {
        super(postDao, channelAccessRights, router);
    }

    @Override
    protected void updateFriendStatus(int channelId) {
        // Keine Freundesstatus Änderung
    }


    @Override
    protected NotificationService getNotificationService() {
        return new BasicNotificationService();
    }
}
