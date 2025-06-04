package com.papas.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService implements FollowUsecase {

    private final FollowPort followPort;

    @Override
    public void follow(Long fromUserId, Long toUserId) {
        followPort.follow(fromUserId, toUserId);
    }

    @Override
    public void unfollowBy(Long fromUserId, Long toUserId) {
        followPort.unfollowBy(fromUserId, toUserId);
    }

    @Override
    public void unfollowBy(Long followId) {
        followPort.unfollowBy(followId);
    }

    @Override
    public List<Follow> getFollowsByFromUserId(Long fromUser) {
        return followPort.getFollowsByFromUserId(fromUser);
    }

    @Override
    public List<Follow> getFollowsByToUserId(Long toUser) {
        return followPort.getFollowsByToUserId(toUser);
    }
}
