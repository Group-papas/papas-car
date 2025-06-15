package com.papas.car;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class TimelineDocumentRepositoryImpl implements TimelineDocumentCustomRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<TimelineDocument> findByFollowerUserIdWithPagination(Long followerUserId, int pageNumber, int pageSize) {
        Query query = new Query()
                .addCriteria(Criteria.where("followerUserId").is(followerUserId))
                .with(
                        PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"))
                );
        return mongoTemplate.find(query, TimelineDocument.class, "timelines");
    }

    @Override
    public void deleteAllByDocumentId(Long documentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("carId").is(documentId));
        mongoTemplate.remove(query, TimelineDocument.class);
    }
}
