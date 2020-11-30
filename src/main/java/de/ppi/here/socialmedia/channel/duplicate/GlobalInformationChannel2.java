package de.ppi.here.socialmedia.channel.duplicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.InsultChecker;
import de.ppi.here.socialmedia.service.RoleService;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.util.UserContext;

@Service
public class GlobalInformationChannel2 implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private InsultChecker insultChecker;

    @Autowired
    private BasicNotificationService notificationService;

    @Autowired
    private RoleService roleService;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws NoWriteAccessRightsException, ContentTooLongException, ContentContainsInsultException {

        if(!roleService.isAdmin(ctx.getUserId())) {
            throw new NoWriteAccessRightsException();
        }

        new ContentLengthCheckStrategy(4000).checkContentLength(post);

        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            throw new ContentContainsInsultException();
        }

        postDao.save(post);

        notificationService.notifySubscribersOfChannel(channelId);
    }
}
