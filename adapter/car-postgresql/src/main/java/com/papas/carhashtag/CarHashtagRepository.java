package com.papas.carhashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarHashtagRepository extends JpaRepository<CarHashtagEntity, Long> {
    Optional<CarHashtagEntity> findByName(String name);

    @Query("""
        SELECT H.name
        FROM CarHashtagMappingEntity CHM
        JOIN CHM.hashtag H ON CHM.hashtag.hashtagId = H.hashtagId
        WHERE CHM.car.carId IN (:carIds)
    """)
    List<String> findHashtag(@Param("carIds") List<Long> carIds);
}
