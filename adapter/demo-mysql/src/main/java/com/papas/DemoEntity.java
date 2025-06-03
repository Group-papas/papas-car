package com.papas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "demo")
@Entity
public class DemoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public DemoEntity(String name) {
        this.name = name;
    }

    public static DemoEntity ofRandom() {
        return new DemoEntity(UUID.randomUUID().toString());
    }
}
