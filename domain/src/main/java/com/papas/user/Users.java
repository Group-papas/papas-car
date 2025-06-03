package com.papas.user;

import com.papas.Gender;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class Users {
    Long id;
    String nickname;
    LocalDate birthday;
    Gender gender;
    LocalDateTime createdAt;

    public static Users generate(String nickname, LocalDate birthday, Gender gender) {
        return new Users(null, nickname, birthday, gender, null);
    }
}
