package com.papas.car.newcar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.papas.CustomObjectMapper;
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
public class NewCarEventProducer implements CarMessageProducePort {

    @Value("${kafka.topic.new-car-event}")
    private String newCarEventTopic;

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendCarMessage(SaveCarEvent event) {
        log.info("kakfa received event :: {}", event);
        try {
            String eventMessage = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(newCarEventTopic, String.valueOf(event.getCarId()), eventMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
