package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;

@Service
public class PrivateGroupChannel extends AbstractPrivateChannel {

    @Autowired
    private BasicNotificationService notificationService;


    @Override
    protected void updateFriendStatus(int channelId) {
        // Keine Freundesstatus Änderung
    }


    @Override
    protected NotificationService getNotificationService() {
        return notificationService;
    }
}
