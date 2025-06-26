package com.papas.car;

import com.papas.ApplicationException;
import com.papas.Errors;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<CarFullInfoDto> carFullInfo = carRepository.findCarFullInfo(carIds);
        return getResolvedCars(carFullInfo);
    }

    @Override
    public ResolvedCar getResolvedCar(Long carId) {
        // 인자를 리스트로 수정하는 이유는 `getResolvedCars` 를 편히 사용하고 싶었다.
        List<CarFullInfoDto> carFullInfo = carRepository.findCarFullInfo(List.of(carId));

        // 1개밖에 없어야 함.
        List<ResolvedCar> resolvedCars = getResolvedCars(carFullInfo);
        if (resolvedCars.size() != 1)
            throw new ApplicationException(Errors.BAD_REQUEST);
        return resolvedCars.getFirst();
    }

    private static ArrayList<ResolvedCar> getResolvedCars(List<CarFullInfoDto> carFullInfo) {
        Map<Long, ResolvedCar> resolvedCarMap = carFullInfo.stream()
                .collect(Collectors.groupingBy(CarFullInfoDto::getCarId)) // carId 기준 그룹핑을 먼저하자..
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            List<CarFullInfoDto> group = entry.getValue();
                            CarFullInfoDto base = group.getFirst();
                            ResolvedCar resolvedCar = base.resolveCar();

                            // nickname 은 나중에 채워~ 왜냐면 app 을 쪼개버렸기 때문에, 나중에 API 통신으로 채워야 함.

                            // 이미지 리스트 생성 (imgId 기준 중복 제거)
                            List<ResolvedCarImage> images = group.stream()
                                    .filter(dto -> dto.getImgId() != null)
                                    .collect(Collectors.toMap(
                                            CarFullInfoDto::getImgId,
                                            dto -> ResolvedCarImage.of(dto.getImgId(), dto.getImgFilePath(), dto.getImgHashName(), dto.getImgExt(), dto.getImgThumbnail())
                                    ))
                                    .values().stream().toList();

                            // 해시태그 리스트 생성 (hashtagId 기준 중복 제거)
                            List<ResolvedHashtag> hashtags = group.stream()
                                    .filter(dto -> dto.getHashtagId() != null)
                                    .collect(Collectors.toMap(
                                            CarFullInfoDto::getHashtagId,
                                            dto -> ResolvedHashtag.of(dto.getHashtagId(), dto.getHashtagName())
                                    ))
                                    .values().stream().toList();

                            resolvedCar.setCarImages(images);
                            resolvedCar.setHashtags(hashtags);

                            return resolvedCar;
                        }
                ));
        return new ArrayList<>(resolvedCarMap.values());
    }


}
