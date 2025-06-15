package com.papas.car;

import com.papas.timeline.TimelinePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscribingNewCarService implements SubscribingNewCarUsecase{

    private final TimelinePort timelinePort;

    @Override
    public void processNewCar(Long carId, List<Long> followerIds) {
        if (followerIds != null && !followerIds.isEmpty()) {
            timelinePort.addContentToFollowerTimelines(carId, followerIds);
        }
    }
}
