package de.ppi.here.socialmedia.bo;

import java.util.List;
import java.util.Map;
import de.ppi.here.socialmedia.exception.NoWriteAccessRightsException;

public class ChannelAccessRights {

    private Map<Integer, List<Integer>> accessRights;

    public void hasAccessRights(Integer channelId, Integer userId) throws NoWriteAccessRightsException {
        if (accessRights.get(channelId).contains(userId)) {
           throw new NoWriteAccessRightsException();
        }
    }
}
