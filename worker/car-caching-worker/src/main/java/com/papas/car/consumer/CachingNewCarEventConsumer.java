package com.papas.car.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.papas.CustomObjectMapper;
import com.papas.car.ResolvingCarUsecase;
import com.papas.car.SaveCarEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CachingNewCarEventConsumer {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final ResolvingCarUsecase resolvingCarUsecase;

    @KafkaListener(
            topics = "#{@kafkaConfigProps.newCarEvent}",
            groupId = "#{@kafkaConfigProps.newCarGroup}",
            concurrency = "1" // 파티션이 1개밖에 없음
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        SaveCarEvent event = objectMapper.readValue(message.value(), SaveCarEvent.class);
        log.info("Received new car event :: carId :: {} ... event :: {}", event.getCarId(), event);
        resolvingCarUsecase.resolveCarAndSave(event.getCarId());
    }
}
