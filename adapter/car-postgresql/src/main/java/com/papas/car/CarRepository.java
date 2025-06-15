package com.papas.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @Query("""
    SELECT new com.papas.car.ResolvedCar(C.carId, C.price, C.year, C.odometer, C.region, C.postingDate, CI.filePath, CI.hashName, CI.extension)
    FROM CarEntity C 
    JOIN C.carImageList CI ON C.carId = CI.car.carId
    WHERE C.carId IN :carIds AND CI.isThumbnail = true
    """)
    List<ResolvedCar> findResolvedCars(@Param("carIds") List<Long> carIds);

    @Query("""
    SELECT C
    FROM CarEntity C 
    JOIN FETCH C.carImageList CI 
    JOIN FETCH C.carHashtagMappings CH
    JOIN FETCH CH.hashtag
    WHERE C.carId IN :carIds AND CI.isThumbnail = true
    """)
    List<CarEntity> findResolvedCarEntity(@Param("carIds") List<Long> carIds);
}
