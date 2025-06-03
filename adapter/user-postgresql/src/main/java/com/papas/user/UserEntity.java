package com.papas.user;

import com.papas.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
   user_id SERIAL NOT NULl PRIMARY KEY, -- 고유 식별 아이디
   nickname VARCHAR(50) NOT NULL, -- 사용자 이름
   birthday DATE NOT NULL, -- 생년월일
   gender VARCHAR(1) NOT NULL, -- 성별
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 가입일
 */
@ToString
@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserEntity(String nickname, LocalDate birthday, Gender gender) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.gender = gender;
        this.createdAt = LocalDateTime.now();
    }
}
