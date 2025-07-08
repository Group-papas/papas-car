package com.papas.timeline;

import com.papas.car.CarPort;
import com.papas.car.ResolvedCar;
import com.papas.car.ResolvingCarUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReadTimelineService implements ReadTimelineUsecase {

    private static final int PAGE_SIZE = 5;

    private final TimelinePort timelinePort;
    // 기존에는 CarPort에 의존했었으나~ 이제는 ResolvingCarUsecase 로 의존시키도록 변경,
    //private final CarPort carPort;
    private final ResolvingCarUsecase resolvingCarUsecase;


    @Override
    public List<ResolvedCar> readTimeline(Long userId, int page) {
        List<Long> carIds = timelinePort.listCarIdsByFollowerUserIdWithPagination(userId, page, PAGE_SIZE);
        //return carPort.readResolvedCars(carIds);
        return resolvingCarUsecase.resolveCarsByIds(carIds);
    }
}
