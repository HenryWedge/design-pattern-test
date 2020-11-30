package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.beans.factory.annotation.Autowired;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.dao.ChannelAuthorizationDao;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.notification.NotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;


public abstract class AbstractChannelBo implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private Router router;

    @Autowired
    private ChannelAuthorizationDao channelAuthorizationDao;


    @Override
    public void postMessage(final Post post, Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException, ContentContainsInsultException {

        if (!hasAccessRights(channelId, ctx.getUserId())) {
            throw new NoWriteAccessRightsException();
        }

        getContentLengthChecker().checkContentLength(post);

        final Post validatedPost = validateContent(post);

        postDao.save(validatedPost);

        updateFriendStatus(channelId);

        refreshPage();

        getNotificationService().notifySubscribersOfChannel(channelId);
    }

    private void refreshPage() {
        this.router.route("message/sent");
    }

    protected boolean hasAccessRights(final Integer channelId, final Integer userId) {
        return channelAuthorizationDao.findAllWhereChannelIdAndUserId(channelId, userId).isEmpty();
    }

    protected abstract ContentLengthCheckStrategy getContentLengthChecker();


    protected abstract Post validateContent(Post post) throws ContentContainsInsultException;


    protected abstract void updateFriendStatus(int channelId);


    protected abstract NotificationService getNotificationService();
}
