package com.papas.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class ResolvedCarCacheAdapter implements ResolvedCarCachePort {

    private static final String RESOLVED_CAR_PREFIX = "resolved_detail_car:v1:";
    private static final Duration TTL = Duration.ofSeconds(60 * 60);

    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(ResolvedCar resolvedCar) {

        String key = getKey(resolvedCar.getCarId());
        String value;
        try {
            value = objectMapper.writeValueAsString(resolvedCar);
        } catch (JsonProcessingException e) {
            log.error("Error ResolvedCarCacheAdapter set");
            throw new RuntimeException(e);
        }
        stringRedisTemplate.opsForValue()
                .set(key, value, TTL);
    }

    @Override
    public ResolvedCar get(Long carId) {
        String value = stringRedisTemplate.opsForValue().get(getKey(carId));
        try {
            return objectMapper.readValue(value, ResolvedCar.class);
        } catch (JsonProcessingException e) {
            log.error("Error ResolvedCarCacheAdapter get");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ResolvedCar> multiGet(List<Long> carIds) {
        List<String> values = stringRedisTemplate.opsForValue().multiGet(carIds.stream().map(this::getKey).toList());
        if (values == null) return List.of();
        return values.stream().filter(Objects::nonNull).map(it -> {
            try {
                return objectMapper.readValue(it, ResolvedCar.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public void delete(Long carId) {
        stringRedisTemplate.delete(getKey(carId));
    }

    private String getKey(Long id) {
        return RESOLVED_CAR_PREFIX + id;
    }
}
