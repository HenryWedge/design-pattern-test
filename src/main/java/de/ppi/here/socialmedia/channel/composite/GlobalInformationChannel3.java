package de.ppi.here.socialmedia.channel.composite;

import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ContentValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ThrowExceptionWhenIncludesInsultValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.IsAdminAccessRightStrategy;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.NotificationService;

@Service
public class GlobalInformationChannel3 extends PostService {

    @Override
    protected AccessRightCheckStrategy getAccessRightCheckService() {
        return new IsAdminAccessRightStrategy();
    }

    @Override
    protected ContentLengthCheckStrategy getContentLengthChecker() {
        return new ContentLengthCheckStrategy(4000);
    }


    @Override
    protected NotificationService getNotificationService() {
        return new BasicNotificationService();
    }


    @Override
    protected ContentValidationStrategy getContentValidator() {
        return new ThrowExceptionWhenIncludesInsultValidationStrategy();
    }

    @Override
    protected FriendStatusUpdater getFriendStatusUpdater() {
        return channelId -> {};
    }
}
