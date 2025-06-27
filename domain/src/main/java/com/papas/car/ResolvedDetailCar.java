package com.papas.car;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ResolvedDetailCar {
    private Long carId; // 중고차 등록 식별키
    private Integer price; // 가격
    private Integer year; // 연식
    private Long odometer; // 주행거리
    private String region; // 지역
    private LocalDateTime postingDate; // 게시 일시
    private String imgFilePath; // 썸네일 파일경로
    private String imgHashName; // 썸네일 해쉬네임
    private String imgExt; // 썸네일 확장자
    private Long userId; // 판매자 아이디
    private String nickname; // 판매자 닉네임
    private List<String> hashtags; // 해쉬태그
}
