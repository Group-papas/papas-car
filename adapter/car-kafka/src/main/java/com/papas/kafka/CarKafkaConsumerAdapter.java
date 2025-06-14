package com.papas.kafka;

import com.papas.car.CarPort;
import com.papas.car.SaveCarEvent;
import com.papas.follow.Follow;
import com.papas.follow.FollowPort;
import com.papas.timeline.TimelinePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CarKafkaConsumerAdapter {

    private final FollowPort followPort;
    private final TimelinePort timelinePort;

    @KafkaListener(
            topics = "${kafka.topic.new-car-event}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void conusmeNewCarEvent(SaveCarEvent event) {
        log.info("Received new car event :: carId :: {} ... event :: {}", event.getCarId(), event);

        // userId로 follow하고 있는 유저에 대한 정보를 불러옵니다.
        List<Follow> follows = followPort.getFollowsByToUserId(event.getUserId());
        if (follows != null && !follows.isEmpty()) {
            // follower들에게 새로 등록된 차량에 대한 정보를 fan-out 하면 끝!
            timelinePort.addContentToFollowerTimelines(event.getCarId(), follows.stream().map(Follow::getFromUser).toList());
        }
    }
}
