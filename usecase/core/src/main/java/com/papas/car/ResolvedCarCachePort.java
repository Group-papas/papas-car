package com.papas.car;

import java.util.List;

public interface ResolvedCarCachePort {
    // 참고로 구현체에서 RedisTemplate 이 제공하는, set, get, multiGet, delete 를 사용하면 될듯함
    void set(ResolvedCar resolvedCar); // 단건 컨텐츠 캐싱
    ResolvedCar get(Long carId); // 캐싱된 단건 컨텐츠 조회
    List<ResolvedCar> multiGet(List<Long> carIds); // 캐싱된 컨텐츠 리스트 조회
    void delete(Long carId); // 캐싱된 컨텐츠 삭제,
}
