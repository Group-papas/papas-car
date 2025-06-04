package com.papas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "follow")
@Entity
public class FollowEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @Column(name = "from_user")
    private Long fromUser;

    @Column(name = "to_user")
    private Long toUser;

    @Column(name = "followed_at")
    private LocalDateTime followedAt;

    private FollowEntity(Long fromUser, Long toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.followedAt = LocalDateTime.now();
    }

    public FollowEntity(Long fromUser, Long toUser, LocalDateTime followedAt) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.followedAt = followedAt;
    }

    public static FollowEntity create(Long fromUser, Long toUser) {
        return new FollowEntity(fromUser, toUser);
    }
}
