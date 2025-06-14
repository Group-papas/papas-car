package com.papas.timeline;

import java.util.List;

public interface TimelinePort {
    void addContentToFollowerTimelines(Long carId, List<Long> followerUserIds);
    void removeCarFromFollowerTimelines(Long carId);
    List<Long> listCarIdsByFollowerUserIdWithPagination(Long followerUserId, int pageNumber, int pageSize);
}
