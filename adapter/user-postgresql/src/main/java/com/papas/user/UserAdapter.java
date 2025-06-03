package com.papas.user;

import com.papas.ApplicationException;
import com.papas.Errors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserAdapter implements UserPort{

    private final UserRepository userRepository;

    @Override
    public Users saveUser(Users users) {
        UserEntity savedUserEntity = userRepository.save(UserEntityConverter.toEntity(users));
        return UserEntityConverter.toModel(savedUserEntity);
    }

    @Override
    public Users findUserBy(Long id) {
        return userRepository.findById(id)
                .map(UserEntityConverter::toModel)
                .orElseThrow(() -> new ApplicationException(Errors.NOT_FOUND_EXCEPTION));
    }

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntityConverter::toModel)
                .toList();
    }
}
