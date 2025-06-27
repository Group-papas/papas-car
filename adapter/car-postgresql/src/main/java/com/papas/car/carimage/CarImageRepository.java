package com.papas.car.carimage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarImageRepository extends JpaRepository<CarImageEntity, Long> {
    List<CarImageEntity> findByImgIdIn(List<Long> imgIds);
}
