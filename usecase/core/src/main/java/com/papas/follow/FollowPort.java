package com.papas.follow;

import java.util.List;

public interface FollowPort {
    void follow(Long fromUserId, Long toUserId);
    void unfollowBy(Long fromUserId, Long toUserId);
    void unfollowBy(Long followId);
    List<Follow> getFollowsByFromUserId(Long fromUser);
    List<Follow> getFollowsByToUserId(Long toUser);
}
