package de.ppi.here.socialmedia.channel.inherit;

import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;


public abstract class AbstractPublicChannel extends AbstractChannel {

    @Override
    protected void updateFriendStatus(final int channelId) {
        // Keine Freundesstatus Änderung
    }


    @Override
    protected NotificationService getNotificationService() {
        return new BasicNotificationService();
    }
}
