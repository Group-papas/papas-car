package com.papas.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "timelines")
public class TimelineDocument {

    @Id
    private String id; // carId 와 followerUserId 의 조합으로 아이디를 만듦
    private Long carId; // 중고차 아이디
    private Long followerUserId; // follower(구독자) user id
    private LocalDateTime createdAt;
    private boolean read; // 해당 구독 컨텐츠 조회 여부

    public static TimelineDocument generate(Long carId, Long followerUserId) {
        return new TimelineDocument(
                String.format("%d_%d", carId, followerUserId),
                carId,
                followerUserId,
                LocalDateTime.now(),
                false
        );
    }
}
