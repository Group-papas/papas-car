package com.papas.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserUsecase {

    private final UserPort userPort;

    @Override
    public Users create(Request request) {
        return userPort.saveUser(Users.generate(request.getNickname(), request.getBirthday(), request.getGender()));
    }

    @Override
    public Users getUserBy(Long id) {
        return userPort.findUserBy(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return userPort.findAllUsers();
    }
}
