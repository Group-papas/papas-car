package com.papas.car.carimage;

import com.papas.car.CarEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_image")
@Entity
public class CarImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "hash_name")
    private String hashName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "extension")
    private String extension;

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    private CarImageEntity(String originName, String hashName, String filePath, String extension, Boolean isThumbnail) {
        this.originName = originName;
        this.hashName = hashName;
        this.filePath = filePath;
        this.extension = extension;
        this.isThumbnail = isThumbnail;
        this.createdAt = LocalDateTime.now();
    }

    public static CarImageEntity of(String originName, String hashName, String filePath, String extension, Boolean isThumbnail) {
        return new CarImageEntity(originName, hashName, filePath, extension, isThumbnail);
    }
}
