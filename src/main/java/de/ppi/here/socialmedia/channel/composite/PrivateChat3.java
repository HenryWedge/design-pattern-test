package de.ppi.here.socialmedia.channel.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.channel.composite.util.FriendStatusUpdater;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.AccessRightCheckStrategyImpl;
import de.ppi.here.socialmedia.service.notification.PushNotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;

@Service
public class PrivateChat3 implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private Router router;

    @Autowired
    private AccessRightCheckStrategyImpl accessRightCheckStrategy;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private FriendStatusUpdater friendStatusUpdater;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException, ContentContainsInsultException {
        final ChannelContext channelContext = new ChannelContext(postDao, router, accessRightCheckStrategy,
            new ContentLengthCheckStrategy(2000), pushNotificationService, post1 -> post1, friendStatusUpdater);
        channelContext.postMessage(post, channelId, ctx);
    }
}
