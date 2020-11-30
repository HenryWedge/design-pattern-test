package de.ppi.here.socialmedia.channel.composite;

import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ContentValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategyImpl;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;


@Service
public class PrivateGroupChannelBo3 extends PostService {

    @Override
    protected AccessRightCheckStrategy getAccessRightCheckService() {
        return new AccessRightCheckStrategyImpl();
    }


    @Override
    protected ContentLengthCheckStrategy getContentLengthChecker() {
        return new ContentLengthCheckStrategy(2000);
    }


    @Override
    protected NotificationService getNotificationService() {
        return new BasicNotificationService();
    }


    @Override
    protected ContentValidationStrategy getContentValidator() {
        return post -> post;
    }


    @Override
    protected FriendStatusUpdater getFriendStatusUpdater() {
        return channelId1 -> { };
    }
}
