package de.ppi.here.socialmedia.channel.composite;

import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ContentValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;

public class ChannelContext implements ChannelBo {

    private PostDao postDao;

    private Router router;

    private AccessRightCheckStrategy accessRightCheckService;

    private ContentLengthCheckStrategy contentLengthChecker;

    private NotificationService notificationService;

    private ContentValidationStrategy contentValidator;

    private FriendStatusUpdater friendStatusUpdater;

    public ChannelContext(final PostDao postDao, final Router router,
        final AccessRightCheckStrategy accessRightCheckService,
        final ContentLengthCheckStrategy contentLengthChecker, final NotificationService notificationService,
        final ContentValidationStrategy contentValidator, final FriendStatusUpdater friendStatusUpdater) {
        this.postDao = postDao;
        this.router = router;
        this.accessRightCheckService = accessRightCheckService;
        this.contentLengthChecker = contentLengthChecker;
        this.notificationService = notificationService;
        this.contentValidator = contentValidator;
        this.friendStatusUpdater = friendStatusUpdater;
    }


    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws NoWriteAccessRightsException, ContentTooLongException, ContentContainsInsultException {

        if (!accessRightCheckService.hasAccessRights(channelId, ctx.getUserId())) {
            throw new NoWriteAccessRightsException();
        }

        contentLengthChecker.checkContentLength(post);

        final Post validatedPost = contentValidator.validateContent(post);

        postDao.save(validatedPost);

        friendStatusUpdater.updateFriendStatus(channelId);

        refreshUI();

        notificationService.notifySubscribersOfChannel(channelId);
    }


    private void refreshUI() {
        router.route("message/sent");
    }

}
