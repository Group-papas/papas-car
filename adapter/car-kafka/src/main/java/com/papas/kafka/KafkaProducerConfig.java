package com.papas.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;


    private final KafkaProperties kafkaProperties;

    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        // Kafka broker 접속 정보
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        // 메세지 키 직렬화
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        // 메세지 값 직렬화
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return props;
//    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
