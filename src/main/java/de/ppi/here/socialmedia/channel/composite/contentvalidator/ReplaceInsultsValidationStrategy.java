package de.ppi.here.socialmedia.channel.composite.contentvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.service.InsultChecker;
import de.ppi.here.socialmedia.service.InsultFilterService;

@Service
public class ReplaceInsultsValidationStrategy implements ContentValidationStrategy {

    @Autowired
    private InsultChecker insultChecker;

    @Autowired
    private InsultFilterService insultFilterService;

    @Override
    public Post validateContent(final Post post) {
        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            post.setContent(insultFilterService.filterInsult(post.getContent()));
        }
        return post;
    }
}
