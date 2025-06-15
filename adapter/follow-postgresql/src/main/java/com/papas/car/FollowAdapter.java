package com.papas.car;

import com.papas.ApplicationException;
import com.papas.Errors;
import com.papas.follow.Follow;
import com.papas.follow.FollowPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FollowAdapter implements FollowPort {

    private final FollowRepository followRepository;

    // 팔로우 메서드
    @Override
    public void follow(Long fromUserId, Long toUserId) {
        followRepository.save(FollowEntity.create(fromUserId, toUserId));
    }

    // 언팔로우 메서드1, fromUser 와 toUser 로 언팔로우 처리
    @Override
    public void unfollowBy(Long fromUserId, Long toUserId) {
        FollowEntity followEntity = followRepository.findByFromUserAndToUser(fromUserId, toUserId)
                .orElseThrow(() -> new ApplicationException(Errors.NOT_FOUND_EXCEPTION));
        followRepository.delete(followEntity);
    }

    // 언팔로우 메서드2, follow 고유 아이디로 언팔로우 처리
    @Override
    public void unfollowBy(Long followId) {
        FollowEntity followEntity = followRepository.findById(followId)
                .orElseThrow(() -> new ApplicationException(Errors.NOT_FOUND_EXCEPTION));
        followRepository.delete(followEntity);
    }

    // 특정 사용자가 팔로우하는 사용자를 조회하는 메서드
    @Override
    public List<Follow> getFollowsByFromUserId(Long fromUser) {
        return followRepository.findByFromUser(fromUser).stream()
                .map(FollowEntityConverter::toModel)
                .toList();
    }

    // 특정 사용자를 팔로우하는 팔로워를 조회하는 메서드
    @Override
    public List<Follow> getFollowsByToUserId(Long toUser) {
        return followRepository.findByToUser(toUser).stream()
                .map(FollowEntityConverter::toModel)
                .toList();
    }
}
