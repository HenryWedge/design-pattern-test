package de.ppi.here.socialmedia.channel.composite.contentvalidator;

import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;

public interface ContentValidationStrategy {

    Post validateContent(final Post post) throws ContentContainsInsultException;

}
