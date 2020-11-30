package de.ppi.here.socialmedia.channel;

import de.ppi.here.socialmedia.bo.Post;
import de.ppi.here.socialmedia.exception.ContentContainsInsultException;
import de.ppi.here.socialmedia.exception.ContentTooLongException;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;
import de.ppi.here.socialmedia.util.UserContext;


public interface ChannelBo {

    void postMessage(final Post post, final Integer channelId, final UserContext ctx)
        throws ContentTooLongException, NoWriteAccessRightsException, ContentContainsInsultException;

}
