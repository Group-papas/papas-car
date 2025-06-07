package com.papas.carhashtag;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hashtag")
@Entity
public class CarHashtagEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Long hashtagId;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public CarHashtagEntity(String name) {
        this.hashtagId = null;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }
}
