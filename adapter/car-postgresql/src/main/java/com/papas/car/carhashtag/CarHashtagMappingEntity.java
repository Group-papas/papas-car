package com.papas.car.carhashtag;

import com.papas.car.CarEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_hashtag_mapping")
@Entity
public class CarHashtagMappingEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_hashtag_id")
    private Long carHashtagId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private CarHashtagEntity hashtag;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private CarHashtagMappingEntity(CarEntity car, CarHashtagEntity hashtag) {
        this.car = car;
        this.hashtag = hashtag;
        this.createdAt = LocalDateTime.now();
    }

    public static CarHashtagMappingEntity of(CarEntity car, CarHashtagEntity hashtag) {
        return new CarHashtagMappingEntity(car, hashtag);
    }
}
