package com.papas.car;

import java.util.List;

public interface TimelineDocumentCustomRepository {
    List<TimelineDocument> findByFollowerUserIdWithPagination(Long followerUserId, int pageNumber, int pageSize);
    void deleteAllByDocumentId(Long documentId);
}
