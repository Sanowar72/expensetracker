package com.geekster.ExpenseTracker.controller;

import com.geekster.ExpenseTracker.Service.ProductService;
import com.geekster.ExpenseTracker.dao.UserDao;
import com.geekster.ExpenseTracker.model.Product;
import com.geekster.ExpenseTracker.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/product")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping(value ="createProduct")
    private ResponseEntity<String> createProduct(@Valid @RequestBody Product product){
        String response = service.createProduct(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping(value ="getProducts")
    private ResponseEntity<List<Product>> getProducts(@RequestParam Integer userId){
        List<Product> response = service.getProducts(userId);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value ="deleteProducts")
    private ResponseEntity<String> deleteProducts(@RequestParam Integer userId,
                                                  @RequestParam Integer productId){
        String response = service.deleteProducts(userId, productId);
        if(response == null)
            return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
        else if (response.equals(""))
            return new ResponseEntity<>("Product not exist", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping(value ="updateProducts")
    private ResponseEntity<String> updateProducts(@Valid @RequestBody Product product,
                                                  @RequestParam Integer userId, @RequestParam Integer productId){
        String response = service.updateProducts(product, userId, productId);
        if(response == null)
            return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
        else if (response.equals(""))
            return new ResponseEntity<>("Product not exist", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
