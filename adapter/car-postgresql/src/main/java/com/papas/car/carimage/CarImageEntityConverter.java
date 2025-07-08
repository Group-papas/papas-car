package com.papas.car.carimage;

import com.papas.carimage.CarImage;

public class CarImageEntityConverter {

    public static CarImageEntity toEntity(CarImage carImage) {
        return CarImageEntity.of(
                carImage.getOriginName(), carImage.getHashName(), carImage.getFilePath(),
                carImage.getExtension(), carImage.getIsThumbnail());
    }

    public static CarImage toModel(CarImageEntity entity) {
        return new CarImage(entity.getImgId(), entity.getOriginName(), entity.getHashName(), entity.getFilePath(),
                entity.getExtension(), entity.getIsThumbnail(), entity.getCar() == null ? null : entity.getCar().getCarId(), entity.getCreatedAt());
    }
}
