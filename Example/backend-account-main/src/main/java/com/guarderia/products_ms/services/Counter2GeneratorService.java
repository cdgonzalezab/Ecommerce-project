package com.guarderia.products_ms.services;

import com.guarderia.products_ms.config.Counter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class Counter2GeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    public Integer getSequenceNumber(String sequenceName){
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq",1);

        Counter2 counter2 = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        Counter2.class);
        return !Objects.isNull(counter2) ? counter2.getSeq() : 1;
    }
}
