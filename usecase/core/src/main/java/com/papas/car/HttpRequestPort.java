package com.papas.car;

import java.util.List;
import java.util.Optional;

public interface HttpRequestPort {
    Optional<String> getUserNickname(Long userId);
    List<String> getHashtags(Long carId);
}
