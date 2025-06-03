package com.papas.controller;

import com.papas.Response;
import com.papas.user.UserUsecase;
import com.papas.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserApiController {

    private final UserUsecase userUsecase;

    @PostMapping
    public Response<Void> save(@RequestBody CreateUserRequest request) {
        userUsecase.create(new UserUsecase.Request(
                request.getNickname(), request.getBirthday(), request.getGender()
        ));
        return Response.justOk();
    }

    @GetMapping("/{userId}")
    public Response<Users> getUser(@PathVariable("userId") Long userId) {
        return Response.success(userUsecase.getUserBy(userId));
    }

    @GetMapping
    public Response<List<Users>> getUsers() {
        return Response.success(userUsecase.getAllUsers());
    }
}
