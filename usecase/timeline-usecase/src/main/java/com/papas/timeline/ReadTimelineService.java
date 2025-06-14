package com.papas.timeline;

import com.papas.car.CarPort;
import com.papas.car.ResolvedCar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReadTimelineService implements ReadTimelineUsecase {

    private static final int PAGE_SIZE = 5;

    private final TimelinePort timelinePort;
    private final CarPort carPort;

    @Override
    public List<ResolvedCar> readTimeline(Long userId, int page) {
        List<Long> carIds = timelinePort.listCarIdsByFollowerUserIdWithPagination(userId, page, PAGE_SIZE);
        return carPort.readResolvedCars(carIds);
    }
}
