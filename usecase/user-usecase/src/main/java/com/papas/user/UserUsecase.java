package com.papas.user;

import com.papas.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public interface UserUsecase {

    Users create(Request request);
    Users getUserBy(Long id);
    List<Users> getAllUsers();
    String getNickname(Long id);

    @Data
    class Request {
        private final String nickname;
        private final LocalDate birthday;
        private final Gender gender;
    }
}
