package com.papas.car.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.papas.CustomObjectMapper;
import com.papas.car.SaveCarEvent;
import com.papas.car.SubscribingNewCarUsecase;
import com.papas.car.FollowUsecase;
import com.papas.follow.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SubscribingNewCarEventConsumer {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final FollowUsecase followUsecase;
    private final SubscribingNewCarUsecase subscribingNewCarUsecase;

    @KafkaListener(
            topics = "#{@kafkaConfigProps.newCarEvent}",
            groupId = "#{@kafkaConfigProps.newCarGroup}",
            concurrency = "1" // 파티션이 1개밖에 없음
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        SaveCarEvent event = objectMapper.readValue(message.value(), SaveCarEvent.class);
        log.info("Received new car event :: carId :: {} ... event :: {}", event.getCarId(), event);
        List<Follow> follows = followUsecase.getFollowsByToUserId(event.getUserId());
        if (follows != null && !follows.isEmpty()) {
            subscribingNewCarUsecase.processNewCar(event.getCarId(), follows.stream().map(Follow::getFromUser).toList());
        }
    }
}
