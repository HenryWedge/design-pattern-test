package de.ppi.here.socialmedia.channel.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.channel.ChannelBo;
import de.ppi.here.socialmedia.channel.composite.contentvalidator.ThrowExceptionWhenIncludesInsultValidationStrategy;
import de.ppi.here.socialmedia.dao.PostDao;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.accessright.IsAdminAccessRightStrategy;
import de.ppi.here.socialmedia.service.notification.BasicNotificationService;
import de.ppi.here.socialmedia.service.router.Router;
import de.ppi.here.socialmedia.util.UserContext;

@Service
public class GlobalInformationChannel3 implements ChannelBo {

    @Autowired
    private PostDao postDao;

    @Autowired
    private Router router;

    @Autowired
    private IsAdminAccessRightStrategy accessRightCheckService;

    @Autowired
    private BasicNotificationService basicNotificationService;

    @Autowired
    private ThrowExceptionWhenIncludesInsultValidationStrategy validationStrategy;

    @Override
    public void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException, ContentContainsInsultException {

        final ChannelContext channelContext =
                new ChannelContextBuilder(postDao, router)
                        .addAccessRightStrategy(accessRightCheckService)
                        .addContentLengthCheckStrategy(new ContentLengthCheckStrategy(4000))
                        .addNotificationService(basicNotificationService)
                        .addContentValidationStrategy(validationStrategy)
                        .build();

        channelContext.postMessage(post, channelId, ctx);
    }
}
