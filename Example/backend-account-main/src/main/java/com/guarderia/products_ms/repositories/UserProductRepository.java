package com.guarderia.products_ms.repositories;

import com.guarderia.products_ms.models.UserProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserProductRepository extends MongoRepository<UserProduct,Integer> {
    List<UserProduct> findByUserId (Integer userId);
    List<UserProduct> findByproductId (Integer productId);
}
