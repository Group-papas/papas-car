package com.papas.controller;

import com.papas.Response;
import com.papas.car.CarUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/cars")
@RestController
public class CarApiController {

    private final CarUsecase carUsecase;

    /**
     * 중고차를 저장하는 API
     */
    @PostMapping
    public Response<Void> create(@RequestBody CreateCarRequest request) {
        carUsecase.create(new CarUsecase.Request(
                request.getPrice(), request.getYear(), request.getManufacturer(), request.getModel(), request.getCondition(),
                request.getEngine(), request.getOdometer(), request.getTitleStatus(), request.getTransmission(), request.getDrive(),
                request.getSize(), request.getType(), request.getColor(), request.getBrand(), request.getVin(), request.getLat(), request.getLon(),
                request.getPostingDate(), request.getRegion(), request.getUserId(), request.getImgIds(), request.getHashtags()
        ));
        return Response.justOk();
    }

    /**
     * 중고차 이미지를 저장하는 API
     */
    @PostMapping("/images")
    public Response<Void> uploadImage(@RequestPart("file") MultipartFile file, @RequestParam("isThumbnail") Boolean isThumbnail) throws IOException {
        carUsecase.saveCarImage(new CarUsecase.SaveImageRequest(file.getBytes(), file.getOriginalFilename(), isThumbnail));
        return Response.justOk();
    }
}
