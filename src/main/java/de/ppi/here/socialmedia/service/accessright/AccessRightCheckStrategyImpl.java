package de.ppi.here.socialmedia.service.accessright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.dao.ChannelAuthorizationDao;

@Service
public class AccessRightCheckStrategyImpl implements AccessRightCheckStrategy {

    @Autowired
    private ChannelAuthorizationDao channelAuthorizationDao;

    @Override
    public boolean checkAccessRights(final Integer channelId, final Integer userId) {
        return channelAuthorizationDao.findAllWhereChannelIdAndUserId(channelId, userId).isEmpty();
    }
}
