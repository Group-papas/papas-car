package com.papas;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationInitializer implements ApplicationRunner {

    private final DemoUsecase demoUsecase;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        demoUsecase.saveRandomDemo();
    }
}
