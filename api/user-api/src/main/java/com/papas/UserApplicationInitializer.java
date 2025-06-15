package com.papas;

import com.papas.user.UserUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "test", matchIfMissing = false)
@Component
public class UserApplicationInitializer implements ApplicationRunner {

    int totalUsers = 10_000;
    int threadPoolSize = 100;
    LocalDate startDate = LocalDate.of(2020, 1, 1);
    LocalDate endDate = LocalDate.of(2025, 1, 1);

    private final ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
    private final CountDownLatch latch = new CountDownLatch(totalUsers);
    private final UserUsecase userUsecase;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < totalUsers; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    userUsecase.create(
                            new UserUsecase.Request(String.format("user%d", finalI), getRandomDateBetween(startDate, endDate), finalI % 2 == 0 ? Gender.M : Gender.F)
                    );
                } finally {
                    latch.countDown();
                }
            });
        }

        // 모든 작업이 끝날 때까지 대기
        latch.await();
        executorService.shutdown();

        System.out.println("모든 사용자 등록 완료!");
    }

    public static LocalDate getRandomDateBetween(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
