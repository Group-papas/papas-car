package com.papas;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.papas.user")
@SpringBootApplication(scanBasePackages = "com.papas")
public class TestConfig {
}
