package de.ppi.here.socialmedia.channel.inherit;

import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.service.ContentLengthCheckStrategy;

public abstract class AbstractPrivateChannel extends AbstractChannel {

    @Override
    protected ContentLengthCheckStrategy getContentLengthChecker() {
        return new ContentLengthCheckStrategy(2000);
    }

    @Override
    protected Post validateContent(Post post) {
        // Keine Inhaltsüberprüfung
        return post;
    }
}
