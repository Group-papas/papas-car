package com.papas.timeline;

public interface CreateTimelineUsecase {
    // timelines 을 저장하는 메서드
    void saveTimelines(Long carId, Long userId);
}
