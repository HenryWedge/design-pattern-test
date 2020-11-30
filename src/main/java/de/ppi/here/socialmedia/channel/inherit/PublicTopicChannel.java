package de.ppi.here.socialmedia.channel.inherit;

import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;
import de.ppi.here.socialmedia.service.InsultChecker;
import de.ppi.here.socialmedia.service.InsultFilterService;

@Service
public class PublicTopicChannel extends AbstractPublicChannelBo {

    private InsultFilterService insultFilterService;
    private InsultChecker insultChecker;

    @Override
    protected ContentLengthCheckStrategy getContentLengthChecker() {
        return new ContentLengthCheckStrategy(2000);
    }


    @Override
    protected Post validateContent(final Post post) {
        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            post.setContent(insultFilterService.filterInsult(post.getContent()));
        }
        return post;
    }
}
