package de.ppi.here.socialmedia.dao;

import java.util.List;
import de.ppi.here.socialmedia.bo.ChannelAuthorization;

public interface ChannelAuthorizationDao {

    List<ChannelAuthorization> findAllWhereChannelIdAndUserId(Integer channelId, Integer userId);

}
