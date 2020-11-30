package de.ppi.here.socialmedia.channel.inherit;

import de.ppi.here.socialmedia.bo.ChannelAccessRights;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;

public abstract class AbstractPublicChannelBo extends AbstractChannelBo {

    @Override
    protected void updateFriendStatus(final int channelId) {
        // Keine Freundesstatus Änderung
    }

    @Override
    protected NotificationService getNotificationService() {
        return new BasicNotificationService();
    }
}
