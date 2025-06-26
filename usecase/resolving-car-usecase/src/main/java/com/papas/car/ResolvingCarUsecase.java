package com.papas.car;

import java.util.List;

public interface ResolvingCarUsecase {
    ResolvedCar resolveCarById(Long carId); // 한건 조회
    List<ResolvedCar> resolveCarsByIds(List<Long> carIds); // 목록(구독페이지, 검색페이지?)
    void resolveCarAndSave(Long carId);
    void deleteResolvedCar(Long carId);
}
