package com.papas.car;

import com.papas.carimage.CarImage;

import java.util.List;
import java.util.Optional;

public interface CarPort {
    Car saveCar(Car car);
    CarImage saveCarImage(CarImage carImage);

    List<ResolvedCar> readResolvedCars(List<Long> carIds);
    ResolvedCar getResolvedCar(Long carId);
}
