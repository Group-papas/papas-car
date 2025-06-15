package com.papas;

import com.papas.car.CarUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "test")
@Component
public class CarApplicationInitializer implements ApplicationRunner {

    int threadPoolSize = 100;

    int latchCount = 9_999;
    int origin = 1;
    int bound = 10_000;
    int normalFollowBound = 100;

    private static final Random random = new Random();
    private final ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
    private final CountDownLatch latch = new CountDownLatch(latchCount);
    private final CarUsecase carUsecase;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        carUsecase.create(new CarUsecase.Request())
    }
}
