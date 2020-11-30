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
import de.ppi.here.socialmedia.service.InsultChecker;
import de.ppi.here.socialmedia.service.InsultFilterService;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;


@Service
public class PublicTopicChannel2 implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private InsultFilterService insultFilterService;

    @Autowired
    private InsultChecker insultChecker;

    @Autowired
    private BasicNotificationService basicNotificationService;

    @Autowired
    private ChannelAuthorizationDao channelAuthorizationDao;

    @Autowired
    private Router router;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException {

        if (channelAuthorizationDao.findAllWhereChannelIdAndUserId(channelId, ctx.getUserId()).isEmpty()) {
            throw new NoWriteAccessRightsException();
        }

        new ContentLengthCheckStrategy(2000).checkContentLength(post);

        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            post.setContent(insultFilterService.filterInsult(post.getContent()));
        }

        postDao.save(post);

        router.route("message/sent");

        basicNotificationService.notifySubscribersOfChannel(channelId);
    }

}
