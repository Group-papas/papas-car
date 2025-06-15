package com.papas.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
    Optional<FollowEntity> findByFromUserAndToUser(Long fromUser, Long toUser);

    List<FollowEntity> findByFromUser(Long fromUser);
    List<FollowEntity> findByToUser(Long toUser);
}
