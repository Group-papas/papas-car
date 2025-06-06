package com.papas.carhashtag;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CarHashtag {
    Long hashtagId;
    String name;
    LocalDateTime createdAt;
}
