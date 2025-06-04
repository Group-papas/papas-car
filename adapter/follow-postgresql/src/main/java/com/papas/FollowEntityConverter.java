package com.papas;

import com.papas.follow.Follow;

public class FollowEntityConverter {

    public static FollowEntity toEntity(Follow follow) {
        return new FollowEntity(follow.getFromUser(), follow.getToUser(), follow.getCreatedAt());
    }

    public static Follow toModel(FollowEntity followEntity) {
        return new Follow(followEntity.getFollowId(), followEntity.getFromUser(), followEntity.getToUser(), followEntity.getFollowedAt());
    }
}
