package com.papas.car;

import com.papas.carhashtag.CarHashtagMappingEntity;
import com.papas.carimage.CarImageEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "car")
@Entity
public class CarEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    Long carId; // 고유 식별 아이디

    @Column(name = "price")
    Integer price; // 판매 가격

    @Column(name = "year")
    Integer year; // 연식

    @Column(name = "manufacturer")
    String manufacturer; // 브랜드명

    @Column(name = "model")
    String model; // 모델명

    @Column(name = "condition")
    String condition; // 상태

    @Column(name = "engine")
    Integer engine; // 엔진 실린더 개수

    @Column(name = "odometer")
    Long odometer; // 주행거리

    @Column(name = "title_status")
    String titleStatus; // 차량 상태

    @Column(name = "transmission")
    String transmission; // 수동/자동

    @Column(name = "drive")
    String drive; // 전륜/후륜/4륜

    @Column(name = "size")
    String size; // 차량 크기

    @Column(name = "type")
    String type; // 차종

    @Column(name = "color")
    String color; // 색상

    @Column(name = "brand")
    String brand; // 브랜드명

    @Column(name = "vin")
    String vin; // 차대 번호

    @Column(name = "lat")
    Double lat; // 위도

    @Column(name = "lon")
    Double lon; // 경도

    @Column(name = "posting_date")
    LocalDateTime postingDate; // 게시 일시

    @Column(name = "region")
    String region; // 판매 지역

    @Column(name = "user_id")
    Long userId; // 판매자 참조 아이디

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImageEntity> carImageList = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarHashtagMappingEntity> carHashtagMappings = new ArrayList<>();

    public CarEntity(Integer price, Integer year, String manufacturer, String model, String condition, Integer engine, Long odometer, String titleStatus, String transmission, String drive, String size, String type, String color, String brand, String vin, Double lat, Double lon, LocalDateTime postingDate, String region, Long userId) {
        this.price = price;
        this.year = year;
        this.manufacturer = manufacturer;
        this.model = model;
        this.condition = condition;
        this.engine = engine;
        this.odometer = odometer;
        this.titleStatus = titleStatus;
        this.transmission = transmission;
        this.drive = drive;
        this.size = size;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.vin = vin;
        this.lat = lat;
        this.lon = lon;
        this.postingDate = postingDate;
        this.region = region;
        this.userId = userId;
    }

    public void addCarImages(List<CarImageEntity> carImages) {
        this.carImageList.addAll(carImages);
        carImages.forEach(it -> it.setCar(this));
    }

    public void addHashtagMappings(List<CarHashtagMappingEntity> carHashtagMappings) {
        this.carHashtagMappings.addAll(carHashtagMappings);
        carHashtagMappings.forEach(it -> it.setCar(this));
    }
}
