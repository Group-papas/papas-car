package com.papas.user;

public class UserEntityConverter {

    public static UserEntity toEntity(Users users) {
        return new UserEntity(users.getNickname(), users.getBirthday(), users.getGender());
    }

    public static Users toModel(UserEntity userEntity) {
        return new Users(userEntity.getUserId(), userEntity.getNickname(), userEntity.getBirthday(), userEntity.getGender(), userEntity.getCreatedAt());
    }
}
