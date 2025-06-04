package com.papas.follow;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Follow {
    Long followId;
    Long fromUser;
    Long toUser;
    LocalDateTime createdAt;
}
