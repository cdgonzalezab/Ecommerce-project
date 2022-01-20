package com.guarderia.products_ms.repositories;

import com.guarderia.products_ms.config.Counter2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Counter2Repository extends MongoRepository<Counter2,String> {
}
