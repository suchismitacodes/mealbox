package com.suchismitacodes.mealbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suchismitacodes.mealbox.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByPname(String name);

    List<Product> findByCategory(String category);

    @Query("SELECT DISTINCT p.category FROM Product p ORDER BY p.category")
    List<String> findDistinctCategories();
}
