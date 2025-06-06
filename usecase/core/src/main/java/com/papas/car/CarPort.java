package com.papas.car;

import com.papas.carimage.CarImage;

public interface CarPort {
    Car saveCar(Car car);
    CarImage saveCarImage(CarImage carImage);
}
