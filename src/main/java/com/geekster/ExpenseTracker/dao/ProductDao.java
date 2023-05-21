package com.geekster.ExpenseTracker.dao;

import com.geekster.ExpenseTracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query(value = "select * from tbl_product p where p.user_id = ?", nativeQuery = true)
    List<Product> getAllProduct(Integer userId);
}

