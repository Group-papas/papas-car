package com.papas.kafka;

import com.papas.car.CarMessageProducePort;
import com.papas.car.SaveCarEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CarKafkaProduceAdapter implements CarMessageProducePort {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Value("${kafka.topic.new-car-event}")
    private String newCarEventTopic;

    /**
     * Kafka message를 발행합니다.
     * @param event
     */
    @Override
    public void sendCarMessage(SaveCarEvent event) {
        Long carId = event.getCarId();
        log.info("kakfa received event :: {}", event);
        kafkaTemplate.send(newCarEventTopic, String.valueOf(carId), event);
        log.info("New enrolled car event sent :: carId :: {}, event :: {}", carId, event);
    }
}
