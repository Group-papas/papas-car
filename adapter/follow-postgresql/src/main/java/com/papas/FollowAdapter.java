package com.papas;

import com.papas.follow.Follow;
import com.papas.follow.FollowPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FollowAdapter implements FollowPort {

    private final FollowRepository followRepository;

    @Override
    public void follow(Long fromUserId, Long toUserId) {
        followRepository.save(FollowEntity.create(fromUserId, toUserId));
    }

    @Override
    public void unfollowBy(Long fromUserId, Long toUserId) {
        FollowEntity followEntity = followRepository.findByFromUserAndToUser(fromUserId, toUserId)
                .orElseThrow(() -> new ApplicationException(Errors.NOT_FOUND_EXCEPTION));
        followRepository.delete(followEntity);
    }

    @Override
    public void unfollowBy(Long followId) {
        FollowEntity followEntity = followRepository.findById(followId)
                .orElseThrow(() -> new ApplicationException(Errors.NOT_FOUND_EXCEPTION));
        followRepository.delete(followEntity);
    }

    @Override
    public List<Follow> getFollowsByFromUserId(Long fromUser) {
        return followRepository.findByFromUser(fromUser).stream()
                .map(FollowEntityConverter::toModel)
                .toList();
    }

    @Override
    public List<Follow> getFollowsByToUserId(Long toUser) {
        return followRepository.findByToUser(toUser).stream()
                .map(FollowEntityConverter::toModel)
                .toList();
    }
}
