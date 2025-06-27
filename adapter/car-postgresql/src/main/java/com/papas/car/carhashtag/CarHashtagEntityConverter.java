package com.papas.car.carhashtag;

import com.papas.carhashtag.CarHashtag;

public class CarHashtagEntityConverter {
    public static CarHashtagEntity toEntity(CarHashtag carHashtag) {
        return new CarHashtagEntity(carHashtag.getName());
    }

    public static CarHashtag toModel(CarHashtagEntity entity) {
        return new CarHashtag(entity.getHashtagId(), entity.getName(), entity.getCreatedAt());
    }
}
