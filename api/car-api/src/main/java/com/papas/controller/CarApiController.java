package com.papas.controller;

import com.papas.Response;
import com.papas.car.CarUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarApiController {

    private final CarUsecase carUsecase;

    @PostMapping
    public Response<Void> create(@RequestBody CreateCarRequest request) {
        carUsecase.create(new CarUsecase.Request(
                request.getPrice(), request.getYear(), request.getManufacturer(), request.getModel(), request.getCondition(),
                request.getEngine(), request.getOdometer(), request.getTitleStatus(), request.getTransmission(), request.getDrive(),
                request.getSize(), request.getType(), request.getColor(), request.getBrand(), request.getVin(), request.getLat(), request.getLon(),
                request.getPostingDate(), request.getRegion(), request.getUserId()
        ));
        return Response.justOk();
    }
}
