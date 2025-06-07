package com.papas.car;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class Car {
    Long carId; // 고유 식별 아이디
    Integer price; // 판매 가격
    Integer year; // 연식
    String manufacturer; // 브랜드명
    String model; // 모델명
    String condition; // 상태
    Integer engine; // 엔진 실린더 개수
    Long odometer; // 주행거리
    String titleStatus; // 차량 상태
    String transmission; // 수동/자동
    String drive; // 전륜/후륜/4륜
    String size; // 차량 크기
    String type; // 차종
    String color; // 색상
    String brand; // 브랜드명
    String vin; // 차대 번호
    Double lat; // 위도
    Double lon; // 경도
    LocalDateTime postingDate; // 게시 일시
    String region; // 판매 지역
    Long userId; // 판매자 참조 아이디
    List<Long> imgIds;
    List<String> hashtags;

    public static Car generate(Integer price, Integer year, String manufacturer, String model, String condition,
                               Integer engine, Long odometer, String titleStatus, String transmission, String drive,
                               String size, String type, String color, String brand, String vin, Double lat, Double lon,
                               LocalDateTime postingDate, String region, Long userId, List<Long> imgIds, List<String> hashtags) {
        return new Car(null, price, year, manufacturer, model, condition, engine, odometer, titleStatus,
                transmission, drive, size, type, color, brand, vin, lat, lon, postingDate, region, userId, imgIds, hashtags);
    }
}
