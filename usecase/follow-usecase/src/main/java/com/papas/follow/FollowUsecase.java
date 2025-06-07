package com.papas.follow;

import java.util.List;

public interface FollowUsecase {
    // 팔로우 메서드
    void follow(Long fromUserId, Long toUserId);
    // 언팔로우 메서드1, fromUser 와 toUser 로 언팔로우 처리
    void unfollowBy(Long fromUserId, Long toUserId);
    // 언팔로우 메서드2, follow 고유 아이디로 언팔로우 처리
    void unfollowBy(Long followId);
    // 특정 사용자가 팔로우하는 사용자를 조회하는 메서드
    List<Follow> getFollowsByFromUserId(Long fromUser);
    // 특정 사용자를 팔로우하는 팔로워를 조회하는 메서드
    List<Follow> getFollowsByToUserId(Long toUser);
}
