package de.ppi.here.socialmedia.channel.composite.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ppi.here.socialmedia.service.FriendStatusUpdateService;

@Service
public class FriendStatusUpdaterImpl implements FriendStatusUpdater {

    @Autowired
    private FriendStatusUpdateService friendStatusUpdateService;

    @Override
    public void updateFriendStatus(final Integer channelId) {
        friendStatusUpdateService.increaseFriendStatus(5, channelId);
    }
}
