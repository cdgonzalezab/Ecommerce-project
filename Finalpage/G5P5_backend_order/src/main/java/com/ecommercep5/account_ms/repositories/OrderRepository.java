package com.ecommercep5.account_ms.repositories;

import com.ecommercep5.account_ms.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository <Order, String>{
    
}