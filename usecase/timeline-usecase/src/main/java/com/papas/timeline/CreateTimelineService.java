package com.papas.timeline;

import com.papas.follow.Follow;
import com.papas.follow.FollowPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateTimelineService implements CreateTimelineUsecase {

    private final FollowPort followPort;
    private final TimelinePort timelinePort;

    @Override
    public void saveTimelines(Long carId, Long userId) {
        // 판매자의 팔로워를 조회한다. using FollowPort
        List<Follow> follows = followPort.getFollowsByToUserId(userId);
        if (follows.isEmpty()) return;

        // follower 들의 고유 식별키를 알아낸다.
        List<Long> followerUserIds = follows.stream().map(Follow::getFromUser).toList();
        timelinePort.addContentToFollowerTimelines(carId, followerUserIds);
    }
}
