package de.ppi.here.socialmedia.channel.composite;

import org.springframework.beans.factory.annotation.Autowired;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ContentValidationStrategy;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;

public abstract class PostService implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private Router router;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws NoWriteAccessRightsException, ContentTooLongException, ContentContainsInsultException {

        if (getAccessRightCheckService().checkAccessRights(channelId, ctx.getUserId())) {
            throw new NoWriteAccessRightsException();
        }

        getContentLengthChecker().checkContentLength(post);

        final Post validatedPost = getContentValidator().validateContent(post);

        postDao.save(validatedPost);

        getFriendStatusUpdater().updateFriendStatus(channelId);

        refreshUI();

        getNotificationService().notifySubscribersOfChannel(channelId);
    }


    private void refreshUI() {
        router.route("message/sent");
    }


    protected abstract AccessRightCheckStrategy getAccessRightCheckService();


    protected abstract ContentLengthCheckStrategy getContentLengthChecker();


    protected abstract NotificationService getNotificationService();


    protected abstract ContentValidationStrategy getContentValidator();


    protected abstract FriendStatusUpdater getFriendStatusUpdater();

}
