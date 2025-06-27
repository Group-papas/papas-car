package com.papas.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;

@SpringBootApplication(exclude = {
//        org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration.class,
//        org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration.class
        ReactiveWebServerFactoryAutoConfiguration.class,
        WebFluxAutoConfiguration.class
})
public class CarCachingWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarCachingWorkerApplication.class, args);
    }
}
