package com.papas.user;

import com.papas.Gender;
import com.papas.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@Transactional
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity("test", LocalDate.of(2000, 1, 1), Gender.M);

        userRepository.save(userEntity);
    }

    @Test
    void test() {
        // given
        final Long id = 1L;

        // when
        UserEntity userEntity = userRepository.findById(id).orElseThrow();

        // then
        Assertions.assertThat(userEntity.getNickname()).isEqualTo("test");
        Assertions.assertThat(userEntity.getBirthday()).isEqualTo(LocalDate.of(2000, 1, 1));
        Assertions.assertThat(userEntity.getGender()).isEqualTo(Gender.M);
    }
}