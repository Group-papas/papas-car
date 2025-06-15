package com.papas.car.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Getter @Setter
@Component
public class KafkaConfigProps {
    @Value("${kafka.topic.new-car-event}")
    private String newCarEvent;

    @Value("${kafka.group.new-car}")
    private String newCarGroup;
}
