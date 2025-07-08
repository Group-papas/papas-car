package com.papas.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ResolvingCarService implements ResolvingCarUsecase {

    private final CarPort carPort;
    private final ResolvedCarCachePort resolvedCarCachePort;
    private final HttpRequestPort httpRequestPort;

    @Override
    public ResolvedCar resolveCarById(Long carId) {
        ResolvedCar car = resolvedCarCachePort.get(carId);
        if (car != null) {
            return car;
        }
        ResolvedCar resolvedCar = carPort.getResolvedCar(carId);
        return complementCar(resolvedCar);
    }

    @Override
    public List<ResolvedCar> resolveCarsByIds(List<Long> carIds) {
        if (carIds == null || carIds.isEmpty()) {
            return List.of();
        }
        // 먼저 캐시에서 찾아봐야 함
        List<ResolvedCar> cachedCars = resolvedCarCachePort.multiGet(carIds);
        // hit 된 것들
        Set<Long> cachedCarIds = cachedCars.stream()
                .map(ResolvedCar::getCarId)
                .collect(Collectors.toSet());
        // missing 된 것듯
        List<Long> missingCarIds = carIds.stream()
                .filter(id -> !cachedCarIds.contains(id))
                .toList();
        // missing 된 것들은 패치해와~
        List<ResolvedCar> fetchedCars = carPort.readResolvedCars(missingCarIds)
                .stream()
                .map(this::complementCar)
                .toList();
        // 합쳐서 반환
        return Stream.concat(cachedCars.stream(), fetchedCars.stream())
                .toList();
    }

    @Override
    public void resolveCarAndSave(Long carId) {
        ResolvedCar resolvedCar = carPort.getResolvedCar(carId);
        complementCar(resolvedCar);
    }

    @Override
    public void deleteResolvedCar(Long carId) {
        resolvedCarCachePort.delete(carId);
    }

    private ResolvedCar complementCar(ResolvedCar resolvedCar) {
        Long userId = resolvedCar.getUserId();
        String nickname = httpRequestPort.getUserNickname(userId)
                .orElseGet(() -> "UNKNOWN");
        resolvedCar.setNickname(nickname);
        resolvedCarCachePort.set(resolvedCar);
        return resolvedCar;
    }

}
