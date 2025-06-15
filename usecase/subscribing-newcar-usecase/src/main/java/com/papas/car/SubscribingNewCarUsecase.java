package com.papas.car;

import java.util.List;

public interface SubscribingNewCarUsecase {
    void processNewCar(Long carId, List<Long> followerIds);
}
