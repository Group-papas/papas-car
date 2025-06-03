package com.papas.user;

import java.util.List;

public interface UserPort {
    Users saveUser(Users users);
    Users findUserBy(Long id);
    List<Users> findAllUsers();
}
