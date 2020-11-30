package de.ppi.here.socialmedia.channel.composite.contentvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.service.InsultChecker;

@Service
public class ThrowExceptionWhenIncludesInsultValidationStrategy implements ContentValidationStrategy {

    @Autowired
    private InsultChecker insultChecker;

    @Override
    public Post validateContent(final Post post) throws ContentContainsInsultException {
        if (insultChecker.isContentIncludingInsult(post.getContent())) {
            throw new ContentContainsInsultException();
        } else {
            return post;
        }
    }
}
