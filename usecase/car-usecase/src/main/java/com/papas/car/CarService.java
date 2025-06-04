package com.papas.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService implements CarUsecase {

    // car rdb port
    private final CarPort carPort;
    // car kafka port
    private final CarMessageProducePort carMessageProducePort;

    @Override
    public Car create(Request request) {
        // 1. DB save
        Car car = carPort.saveCar(
                Car.generate(request.getPrice(), request.getYear(), request.getManufacturer(), request.getModel(),
                        request.getCondition(), request.getEngine(), request.getOdometer(), request.getTitleStatus(),
                        request.getTransmission(), request.getDrive(), request.getSize(), request.getType(), request.getColor(),
                        request.getBrand(), request.getVin(), request.getLat(), request.getLon(), request.getPostingDate(),
                        request.getRegion(), request.getUserId()
                )
        );
        // 2. Kafka message 발행처리. with CarMessageProducePort
        SaveCarEvent saveCarEvent = SaveCarEvent.of(car.getUserId(), car.getCarId());
        carMessageProducePort.sendCarMessage(saveCarEvent);

        return null;
    }
}
