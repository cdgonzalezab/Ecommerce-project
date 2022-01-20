package com.guarderia.products_ms.repositories;

import com.guarderia.products_ms.models.Product;
import com.guarderia.products_ms.models.UserProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository <Product, Integer> {
    List<Product> findByIsService (Boolean isService);
}
