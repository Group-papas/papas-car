package com.papas;

import com.papas.car.FollowUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "test")
@Component
public class FollowApplicationInitializer implements ApplicationRunner {

    int threadPoolSize = 100;

    int latchCount = 9_999;
    int origin = 1;
    int bound = 10_000;
    int normalFollowBound = 100;

    private static final Random random = new Random();
    private final ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
    private final CountDownLatch latch = new CountDownLatch(latchCount);
    private final FollowUsecase followUsecase;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 우선 `UserApplication` 에서 테스트 데이터를 다 등록했다는 가정이 필요하다. 왜냐? 사용자가 있어야 follow 를 하던 말던 ㅋ
        for (int i = 1; i < bound; i++) {
            int fromUserId = i;
            executorService.submit(() -> {
                try {
                    // 1 ~ 99 의 랜덤 숫자를 뽑는다.
                    int followCount = getRandom(origin, normalFollowBound);
                    IntStream.range(0, followCount).forEach(it -> {
                        // 1 ~ 9999 의 랜덤 숫자임
                        int toUserId = getRandom(origin, bound);
                        while (fromUserId == toUserId) {
                            // 1 ~ 9999 의 랜덤 숫자임
                            toUserId = getRandom(origin, bound);
                        }

                        // fromUserId 가 toUserId 를 follow 처리한다.
                        followUsecase.follow((long) fromUserId, (long) toUserId);
                    });

                    // 그리고 userId 가 10000 인 특정 유저를 모든 유저가 follow 한다고 가정한다.
                    followUsecase.follow((long) fromUserId, (long) bound);
                } finally {
                    latch.countDown();
                }
            });
        }

        // 모든 작업이 끝날 때까지 대기
        latch.await();
        executorService.shutdown();

        System.out.println("모든 팔로우 등록 완료!");
    }

    public static int getRandom(int origin, int bound) {
        return random.nextInt(origin, bound);
    }
}
