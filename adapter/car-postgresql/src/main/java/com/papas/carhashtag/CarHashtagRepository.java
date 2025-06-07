package com.papas.carhashtag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarHashtagRepository extends JpaRepository<CarHashtagEntity, Long> {
    Optional<CarHashtagEntity> findByName(String name);
}
