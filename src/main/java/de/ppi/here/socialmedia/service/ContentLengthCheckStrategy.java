package de.ppi.here.socialmedia.service;

import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentTooLongException;

public class ContentLengthCheckStrategy {

    private final int maxLength;

    public ContentLengthCheckStrategy(final int maxLength) {
        this.maxLength = maxLength;
    }

    public void checkContentLength(Post post) throws ContentTooLongException {
        if(post.getContent().length() > maxLength) {
            throw new ContentTooLongException();
        }
    }
}
