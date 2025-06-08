package com.papas.car;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration
public class MongoConfig {

//    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        // 이 부분이 잘못되어 있으면 문제가 발생함
        return new MongoTemplate(mongoClient(), "papascar");
    }

    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://admin:1234@localhost:27017/papascar?authSource=admin");
    }
}
