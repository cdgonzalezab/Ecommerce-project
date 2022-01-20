package com.guarderia.products_ms.controllers;

import com.guarderia.products_ms.services.Counter2GeneratorService;
import com.guarderia.products_ms.exceptions.ProductIdAlreadyUsedException;
import com.guarderia.products_ms.exceptions.ProductNotFoundException;
import com.guarderia.products_ms.models.Product;
import com.guarderia.products_ms.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductsRepository productsRepository;
    private Counter2GeneratorService service2;

    public ProductController(ProductsRepository productsRepository, Counter2GeneratorService service2) {
        this.productsRepository = productsRepository;
        this.service2 = service2;
    }

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable Integer id){
        return productsRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No se encontro el producto con id: " + id));
    }

    @GetMapping("/products/")
    List<Product> getAllProducts(){
        return productsRepository.findAll();
    }

    @GetMapping("/products/{state}")
    List<Product> getProductIsService(@PathVariable Boolean state) {
        return productsRepository.findByIsService(state);
    }

    @PostMapping("/product")
    Product newProduct(@RequestBody Product product){
        product.setId(service2.getSequenceNumber(Product.SEQUENCE_NAME2));

        Product mio = productsRepository.findById(product.getId())
                .orElse(null);

        if (mio != null)
            throw  new ProductIdAlreadyUsedException("The id:"+ product.getId() + "is already in use");

        return productsRepository.save(product);
    }

    @PutMapping("/product/{id}")
    Product modifyProduct(@RequestBody Product product, @PathVariable Integer id){
        Product mio = productsRepository.findById(id)
                .orElse(null);
        if (mio == null)
            throw  new ProductNotFoundException("No se encontro el producto con id: " + id);

        product.setId(id);
        return productsRepository.save(product);
    }

    @DeleteMapping("product/{id}")
    String deleteProduct(@PathVariable Integer id){
        Product mio = productsRepository.findById(id)
                        .orElse(null);
        if (mio == null)
            throw  new ProductNotFoundException("No se encontro el producto con id: " + id);

        productsRepository.deleteById(id);
        return "producto eliminado";
    }

}
