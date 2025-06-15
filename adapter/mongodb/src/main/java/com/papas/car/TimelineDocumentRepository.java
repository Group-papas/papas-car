package com.papas.car;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimelineDocumentRepository extends TimelineDocumentCustomRepository, MongoRepository<TimelineDocument, String> {
}
