package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.InsultChecker;
import de.ppi.here.socialmedia.service.RoleService;

@Service
public class GlobalInformationChannel extends AbstractPublicChannel {

    @Autowired
    private InsultChecker insultChecker;

    @Autowired
    private RoleService roleService;

    @Override
    protected ContentLengthCheckStrategy getContentLengthChecker() {
        return new ContentLengthCheckStrategy(4000);
    }

    @Override
    protected boolean hasAccessRights(final Integer channelId, final Integer userId) {
        return roleService.isAdmin(userId);
    }

    @Override
    protected Post validateContent(Post post) throws ContentContainsInsultException {
        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            throw new ContentContainsInsultException();
        } else {
            return post;
        }
    }
}
