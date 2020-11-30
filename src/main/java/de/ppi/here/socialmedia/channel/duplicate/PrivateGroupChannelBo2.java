package de.ppi.here.socialmedia.channel.duplicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.dao.ChannelAuthorizationDao;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.FriendStatusUpdateService;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.notification.PushNotificationService;
import de.ppi.here.socialmedia.util.UserContext;


@Service
public class PrivateGroupChannelBo2 implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private FriendStatusUpdateService friendStatusUpdateService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private BasicNotificationService basicNotificationService;

    @Autowired
    private ChannelAuthorizationDao channelAuthorizationDao;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException {

        if (channelAuthorizationDao.findAllWhereChannelIdAndUserId(channelId, ctx.getUserId()).isEmpty()) {
            throw new NoWriteAccessRightsException();
        }

        new ContentLengthCheckStrategy(2000).checkContentLength(post);

        postDao.save(post);

        basicNotificationService.notifySubscribersOfChannel(channelId);
    }

}
