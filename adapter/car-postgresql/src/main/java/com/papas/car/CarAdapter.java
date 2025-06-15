package com.papas.car;

import com.papas.carhashtag.CarHashtagEntity;
import com.papas.carhashtag.CarHashtagMappingEntity;
import com.papas.carhashtag.CarHashtagMappingRepository;
import com.papas.carhashtag.CarHashtagRepository;
import com.papas.carimage.CarImage;
import com.papas.carimage.CarImageEntity;
import com.papas.carimage.CarImageEntityConverter;
import com.papas.carimage.CarImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CarAdapter implements CarPort{

    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;
    private final CarHashtagRepository carHashtagRepository;
    private final CarHashtagMappingRepository carHashtagMappingRepository;

    @Override
    public Car saveCar(Car car) {
        // Car 엔티티 먼저 저장!
        CarEntity savedCar = carRepository.save(CarEntityConverter.toEntity(car));

        // 그리고 저장한 이미지 엔티티를 가지고 와서 Car 엔티티와 연관관계 설정!
        List<CarImageEntity> carImages = carImageRepository.findByImgIdIn(car.getImgIds());
        savedCar.addCarImages(carImages);

        List<Long> imgIds = carImages.stream().map(CarImageEntity::getImgId).toList();

        List<String> hashtags = car.getHashtags();
        // 해쉬태그와 해쉬태그매핑, 그리고 Car 엔티티와 연관관계 설정!
        if (hashtags != null && !hashtags.isEmpty()) {
            // 우선 이미 저장된 해쉬태그인지 조회를 해보고, 없는 해쉬태그는 저장 처리.
            List<CarHashtagEntity> hashtagEntities = hashtags.stream()
                    .map(it ->
                            carHashtagRepository.findByName(it)
                                    .orElseGet(() -> carHashtagRepository.save(new CarHashtagEntity(it))
                            )
                    ).toList();
            // 해쉬태그매핑 엔티티을 만들어낸다.
            List<CarHashtagMappingEntity> carHashtagMappingEntities = hashtagEntities.stream()
                    .map(it -> CarHashtagMappingEntity.of(savedCar, it)).toList();
            // 해쉬태그매핑 엔티티를 연관관계 설정한다.
            savedCar.addHashtagMappings(carHashtagMappingEntities);
            // 해쉬태그매핑 엔티티를 저장
            carHashtagMappingRepository.saveAll(carHashtagMappingEntities);
        }
        // 모델객체로 반환.
        return CarEntityConverter.toModel(savedCar, imgIds, hashtags);
    }

    @Override
    public CarImage saveCarImage(CarImage carImage) {
        CarImageEntity entity = CarImageEntityConverter.toEntity(carImage);
        carImageRepository.save(entity);
        return CarImageEntityConverter.toModel(entity);
    }

    @Override
    public List<ResolvedCar> readResolvedCars(List<Long> carIds) {
        List<ResolvedCar> resolvedCars = carRepository.findResolvedCars(carIds);
        return resolvedCars;
    }
}
