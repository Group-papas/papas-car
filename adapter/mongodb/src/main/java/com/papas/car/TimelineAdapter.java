package com.papas.car;

import com.papas.timeline.TimelinePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TimelineAdapter implements TimelinePort {

    private final TimelineDocumentRepository timelineDocumentRepository;

    @Override
    public void addContentToFollowerTimelines(Long carId, List<Long> followerUserIds) {
        List<TimelineDocument> documents = followerUserIds.stream().map(it -> TimelineDocument.generate(carId, it)).toList();
        timelineDocumentRepository.saveAll(documents);
    }

    @Override
    public void removeCarFromFollowerTimelines(Long carId) {
        timelineDocumentRepository.deleteAllByDocumentId(carId);
    }

    @Override
    public List<Long> listCarIdsByFollowerUserIdWithPagination(Long followerUserId, int pageNumber, int pageSize) {
        List<TimelineDocument> timelines = timelineDocumentRepository.findByFollowerUserIdWithPagination(followerUserId, pageNumber, pageSize);
        return timelines.stream().map(TimelineDocument::getCarId).toList();
    }
}
