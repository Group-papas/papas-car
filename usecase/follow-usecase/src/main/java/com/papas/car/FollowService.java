package com.papas.car;

import com.papas.follow.Follow;
import com.papas.follow.FollowPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService implements FollowUsecase {

    private final FollowPort followPort;

    // 팔로우 메서드
    @Override
    public void follow(Long fromUserId, Long toUserId) {
        followPort.follow(fromUserId, toUserId);
    }
    // 언팔로우 메서드1, fromUser 와 toUser 로 언팔로우 처리
    @Override
    public void unfollowBy(Long fromUserId, Long toUserId) {
        followPort.unfollowBy(fromUserId, toUserId);
    }

    // 언팔로우 메서드2, follow 고유 아이디로 언팔로우 처리
    @Override
    public void unfollowBy(Long followId) {
        followPort.unfollowBy(followId);
    }

    // 특정 사용자가 팔로우하는 사용자를 조회하는 메서드
    @Override
    public List<Follow> getFollowsByFromUserId(Long fromUser) {
        return followPort.getFollowsByFromUserId(fromUser);
    }

    // 특정 사용자를 팔로우하는 팔로워를 조회하는 메서드
    @Override
    public List<Follow> getFollowsByToUserId(Long toUser) {
        return followPort.getFollowsByToUserId(toUser);
    }
}
