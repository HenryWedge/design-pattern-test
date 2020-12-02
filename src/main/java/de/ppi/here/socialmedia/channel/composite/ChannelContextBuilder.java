package de.ppi.here.socialmedia.channel.composite;

import de.ppi.here.socialmedia.channel.composite.contentvalidator.ContentValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;

public class ChannelContextBuilder {

    private PostDao postDao;

    private Router router;

    private AccessRightCheckStrategy accessRightCheckService;

    private ContentLengthCheckStrategy contentLengthChecker;

    private NotificationService notificationService;

    private ContentValidationStrategy contentValidator;

    private FriendStatusUpdater friendStatusUpdater;

    public ChannelContextBuilder(final PostDao postDao, final Router router) {
        this.postDao = postDao;
        this.router = router;
    }

    public ChannelContextBuilder addAccessRightStrategy(AccessRightCheckStrategy accessRightCheckStrategy) {
        this.accessRightCheckService = accessRightCheckStrategy;
        return this;
    }

    public ChannelContextBuilder addContentLengthCheckStrategy(ContentLengthCheckStrategy contentLengthChecker) {
        this.contentLengthChecker = contentLengthChecker;
        return this;
    }

    public ChannelContextBuilder addNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
        return this;
    }

    public ChannelContextBuilder addContentValidationStrategy(
        ContentValidationStrategy contentValidationStrategy) {
        this.contentValidator = contentValidationStrategy;
        return this;
    }

    public ChannelContextBuilder addFriendStatusUpdater(FriendStatusUpdater friendStatusUpdater) {
        this.friendStatusUpdater = friendStatusUpdater;
        return this;
    }

    public ChannelContext build() {
        return new ChannelContext(postDao, router, accessRightCheckService, contentLengthChecker,
            notificationService, contentValidator, friendStatusUpdater);
    }
}
