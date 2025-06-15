package com.papas.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResolvedCar {
    private Long carId; // 중고차 등록 식별키
    private Integer price; // 가격
    private Integer year; // 연식
    private Long odometer; // 주행거리
    private String region; // 지역
    private LocalDateTime postingDate; // 게시 일시
    private String imgFilePath; // 썸네일 파일경로
    private String imgHashName; // 썸네일 해쉬네임
    private String imgExt; // 썸네일 확장자
    private List<String> hashtags; // 해쉬태그

    public ResolvedCar(Long carId, Integer price, Integer year, Long odometer, String region, LocalDateTime postingDate, String imgFilePath, String imgHashName, String imgExt) {
        this.carId = carId;
        this.price = price;
        this.year = year;
        this.odometer = odometer;
        this.region = region;
        this.postingDate = postingDate;
        this.imgFilePath = imgFilePath;
        this.imgHashName = imgHashName;
        this.imgExt = imgExt;
    }
}
