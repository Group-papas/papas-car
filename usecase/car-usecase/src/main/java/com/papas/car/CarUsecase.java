package com.papas.car;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public interface CarUsecase {

    Car create(Request request);

    @AllArgsConstructor
    @Data
    class Request {
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
    }
}
