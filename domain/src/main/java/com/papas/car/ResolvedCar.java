package com.papas.car;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class ResolvedCar {
    private Long carId; // 중고차 등록 식별키
    private Integer price; // 가격
    private Integer year; // 연식
    private Long odometer; // 주행거리
    private String region; // 지역
    private LocalDateTime postingDate; // 게시 일시

    private Long userId; // 판매자 아이디

    private String nickname;
    private List<ResolvedCarImage> carImages;
    private List<ResolvedHashtag> hashtags;

    private ResolvedCar(Long carId, Integer price, Integer year, Long odometer, String region, LocalDateTime postingDate, Long userId) {
        this.carId = carId;
        this.price = price;
        this.year = year;
        this.odometer = odometer;
        this.region = region;
        this.postingDate = postingDate;
        this.userId = userId;
    }

    public static ResolvedCar of(Long carId, Integer price, Integer year, Long odometer, String region, LocalDateTime postingDate, Long userId) {
        return new ResolvedCar(carId, price, year, odometer, region, postingDate, userId);
    }
}
