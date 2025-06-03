package com.papas;

import com.papas.demo.DemoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DemoAdapter implements DemoPort {

    private final DemoRepository demoRepository;

    @Override
    public String getNameBy(Long id) {
        return demoRepository.findById(id)
                .map(DemoEntity::getName)
                .orElseThrow();
    }

    @Override
    public void saveRandomDemo() {
        DemoEntity entity = DemoEntity.ofRandom();
        log.info("save random demo: {}", entity);
        demoRepository.saveAndFlush(entity);
    }
}
