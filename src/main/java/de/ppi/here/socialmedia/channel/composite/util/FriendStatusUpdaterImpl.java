package de.ppi.here.socialmedia.channel.composite.util;

import de.ppi.here.socialmedia.service.FriendStatusUpdateService;

public class FriendStatusUpdaterImpl implements FriendStatusUpdater {

    final FriendStatusUpdateService friendStatusUpdateService = new FriendStatusUpdateService();

    @Override
    public void updateFriendStatus(final Integer channelId) {
        friendStatusUpdateService.increaseFriendStatus(5, channelId);
    }
}
