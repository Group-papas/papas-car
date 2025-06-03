package com.papas;

import com.papas.demo.DemoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DemoService implements DemoUsecase {

    private final DemoPort demoPort;

    @Override
    public String getDemoNameBy(Long id) {
        return demoPort.getNameBy(id);
    }

    @Override
    public void saveRandomDemo() {
        demoPort.saveRandomDemo();
    }
}
