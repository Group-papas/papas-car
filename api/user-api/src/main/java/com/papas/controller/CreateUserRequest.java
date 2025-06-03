package com.papas.controller;

import com.papas.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserRequest {
    private String nickname;
    private LocalDate birthday;
    private Gender gender;
}
