package com.guarderia.products_ms.controllers;

import com.guarderia.products_ms.exceptions.AlreadyAddedException;
import com.guarderia.products_ms.exceptions.ProductNotFoundException;
import com.guarderia.products_ms.models.UserProduct;
import com.guarderia.products_ms.services.CounterGeneratorService;
import org.springframework.web.bind.annotation.*;
import com.guarderia.products_ms.repositories.UserProductRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserProductController {
    private UserProductRepository userProductRepository;
    private CounterGeneratorService service;

    public UserProductController(UserProductRepository userProductRepository, CounterGeneratorService service) {
        this.userProductRepository = userProductRepository;
        this.service = service;
    }


    @PostMapping("/item")
    public UserProduct save(@RequestBody UserProduct userProduct){
        List<UserProduct> userProducts = userProductRepository.findByUserId(userProduct.getUserId());

        List<UserProduct> userPrdctsFiltered = new ArrayList<UserProduct>();
        for (UserProduct userProduct1 : userProducts) {
            if ( userProduct1.getProductId() == userProduct.getProductId())
                userPrdctsFiltered.add(userProduct1);
        }

        if (!userPrdctsFiltered.isEmpty())
            throw new AlreadyAddedException("The product "+ userProduct.getProductId() + "Was already added");

        if (userProduct.getQuantity() <= 0)
            userProduct.setQuantity(1);

        userProduct.setId(service.getSequenceNumber(UserProduct.SEQUENCE_NAME));
        return userProductRepository.save(userProduct);
    }

    @PutMapping("/item")
    public UserProduct updateItem(@RequestBody UserProduct userProduct){
        List<UserProduct> userProducts = userProductRepository.findByUserId(userProduct.getUserId());

        UserProduct userPrdctsFiltered = null;
        for (UserProduct userProduct1 : userProducts) {
            if ( userProduct1.getProductId() == userProduct.getProductId())
                userPrdctsFiltered = userProduct1;
        }

        if (userPrdctsFiltered == null)
            throw new ProductNotFoundException("The product or user was not found");

        userPrdctsFiltered.setQuantity(userProduct.getQuantity());

        if (userPrdctsFiltered.getQuantity() <= 0)
            userPrdctsFiltered.setQuantity(1);

        userPrdctsFiltered.setId(userPrdctsFiltered.getId());
        return userProductRepository.save(userPrdctsFiltered);
    }

    @GetMapping("/items/{id}")
    public List<UserProduct> getItems(@PathVariable Integer id){
        return userProductRepository.findByUserId(id);
    }

    @GetMapping("/item/{userId}/{productId}")
    public UserProduct getItem(@PathVariable Integer userId, @PathVariable Integer productId){
        List<UserProduct> userProducts = userProductRepository.findByUserId(userId);
        UserProduct mine = null;
        for (UserProduct userProduct1 : userProducts) {
            if ( userProduct1.getProductId() == productId)
                mine = userProduct1;
        }

        if (mine == null)
            throw new ProductNotFoundException("the product "+ productId + "Was not found in user " + userId + ".");

        return mine;
    }

    @DeleteMapping("/item/userId/{userId}")
    public String deleteItemsByUserId(@PathVariable Integer userId){
        List <UserProduct> items = userProductRepository.findByUserId(userId);
        List<String> idErased = new ArrayList<>();
        for (UserProduct myProducts: items) {
                idErased.add(myProducts.getProductId().toString());
                userProductRepository.deleteById(myProducts.getId());
            }
        return ("Products id Erased: " + idErased.toString());
    }

    @DeleteMapping("/item/productId/{productId}")
    public String deleteItemsByProductId(@PathVariable Integer productId){
        List <UserProduct> items = userProductRepository.findByproductId(productId);
        for (UserProduct myProduct: items) {
            userProductRepository.deleteById(myProduct.getId());
        }
        return ("Products id Erased: " + productId);
    }

    @DeleteMapping("/item/{userId}/{productId}")
    public  String deleteItem(@PathVariable Integer userId, @PathVariable Integer productId){
        List<UserProduct> userProducts = userProductRepository.findByUserId(userId);
        UserProduct mine = null;
        for (UserProduct userProduct1 : userProducts) {
            if ( userProduct1.getProductId() == productId)
                mine = userProduct1;
        }

        if (mine == null)
            throw new ProductNotFoundException("the product id "+ productId + "Was not found in user " + userId + ".");

        String IdErased = mine.getProductId().toString();
        userProductRepository.deleteById(mine.getId());
        return  ("Product id deleted: " + IdErased);
    }

}
