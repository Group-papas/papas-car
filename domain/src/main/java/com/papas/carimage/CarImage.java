package com.papas.carimage;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CarImage {
    Long imgId;
    String originName;
    String hashName;
    String filePath;
    String extension;
    Boolean isThumbnail;
    Long carId;
    LocalDateTime createdAt;

    public static CarImage generate(String originName, String hashName, String filePath, String extension,
                                    Boolean isThumbnail, Long carId) {
        return new CarImage(null, originName, hashName, filePath, extension, isThumbnail, carId, null);
    }
}
