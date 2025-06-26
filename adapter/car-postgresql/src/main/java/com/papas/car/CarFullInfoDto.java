package com.papas.car;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * `CarEntity` 는 `CarImageEntity`, `CarHashtagEntity` 와 각각 1:N 연관관계를 갖고 있다.
 * 그렇다 보니 jpa 의 fetch join 을 시도할 경우 car 데이터가 중복되는 이슈가 있다.
 * 그러니 JOIN 및 DTO 로 조회를 하고 처리해주자.
 */
@NoArgsConstructor
@Data
public class CarFullInfoDto {
    /* car part */
    private Long carId; // 중고차 등록 식별키
    private Integer price; // 가격
    private Integer year; // 연식
    private Long odometer; // 주행거리
    private String region; // 지역
    private LocalDateTime postingDate; // 게시 일시
    private Long userId; // 판매자 아이디

    /* car image part */
    private Long imgId; // 이미지 식별키
    private String imgFilePath; // 이미지 파일경로
    private String imgHashName; // 이미지 해쉬네임
    private String imgExt; // 이미지 확장자
    private Boolean imgThumbnail; // 썸네일 여부

    /* car hashtag part */
    private Long hashtagId; // 해쉬태그 식별키
    private String hashtagName; // 해쉬태그 이름

    public CarFullInfoDto(Long carId, Integer price, Integer year,
                          Long odometer, String region, LocalDateTime postingDate, Long userId,
                          Long imgId, String imgFilePath, String imgHashName, String imgExt, Boolean imgThumbnail,
                          Long hashtagId, String hashtagName) {
        this.carId = carId;
        this.price = price;
        this.year = year;
        this.odometer = odometer;
        this.region = region;
        this.postingDate = postingDate;
        this.userId = userId;
        this.imgId = imgId;
        this.imgFilePath = imgFilePath;
        this.imgHashName = imgHashName;
        this.imgExt = imgExt;
        this.imgThumbnail = imgThumbnail;
        this.hashtagId = hashtagId;
        this.hashtagName = hashtagName;
    }

    public ResolvedCar resolveCar() {
        return ResolvedCar.of(carId, price, year, odometer, region, postingDate, userId);
    }

    public ResolvedCarImage resolvedCarImage() {

    }
}
