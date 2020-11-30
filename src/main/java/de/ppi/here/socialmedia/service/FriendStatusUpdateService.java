package de.ppi.here.socialmedia.service;

import org.springframework.stereotype.Service;

@Service
public interface FriendStatusUpdateService {

    void increaseFriendStatus(int improvement, int userId);
}
