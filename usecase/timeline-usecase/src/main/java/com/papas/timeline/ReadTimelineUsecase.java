package com.papas.timeline;

import com.papas.car.ResolvedCar;

import java.util.List;

public interface ReadTimelineUsecase {
    List<ResolvedCar> readTimeline(Long userId, int page);
}
