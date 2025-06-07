package com.papas.car;

import java.util.List;

public class CarEntityConverter {

    public static CarEntity toEntity(Car car) {
        return new CarEntity(car.getPrice(), car.getYear(), car.getManufacturer(), car.getModel(), car.getCondition(),
                car.getEngine(), car.getOdometer(), car.getTitleStatus(), car.getTransmission(), car.getDrive(), car.getSize(),
                car.getType(), car.getColor(), car.getBrand(), car.getVin(), car.getLat(), car.getLon(), car.getPostingDate(),
                car.getRegion(), car.getUserId());
    }

    public static Car toModel(CarEntity carEntity, List<Long> imgIds, List<String> hashtags) {
        return new Car(carEntity.getCarId(), carEntity.getPrice(), carEntity.getYear(), carEntity.getManufacturer(),
                carEntity.getModel(), carEntity.getCondition(), carEntity.getEngine(), carEntity.getOdometer(),
                carEntity.getTitleStatus(), carEntity.getTransmission(), carEntity.getDrive(), carEntity.getSize(),
                carEntity.getType(), carEntity.getColor(), carEntity.getBrand(), carEntity.getVin(), carEntity.getLat(),
                carEntity.getLon(), carEntity.getPostingDate(), carEntity.getRegion(), carEntity.getUserId(), imgIds, hashtags);
    }
}
