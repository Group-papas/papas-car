package com.papas.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class CreateCarRequest {
    private Integer price; // 판매 가격
    private Integer year; // 연식
    private String manufacturer; // 브랜드명
    private String model; // 모델명
    private String condition; // 상태
    private Integer engine; // 엔진 실린더 개수
    private Long odometer; // 주행거리
    private String titleStatus; // 차량 상태
    private String transmission; // 수동/자동
    private String drive; // 전륜/후륜/4륜
    private String size; // 차량 크기
    private String type; // 차종
    private String color; // 색상
    private String brand; // 브랜드명
    private String vin; // 차대 번호
    private Double lat; // 위도
    private Double lon; // 경도
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postingDate; // 게시 일시
    private String region; // 판매 지역
    private Long userId; // 판매자 참조 아이디
    private List<Long> imgIds;
    private List<String> hashtags;
}
