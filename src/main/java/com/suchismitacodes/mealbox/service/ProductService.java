package com.suchismitacodes.mealbox.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.suchismitacodes.mealbox.entity.Product;
import com.suchismitacodes.mealbox.repository.ProductRepository;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void updateProduct(Product product, int id) {
        product.setPid(id);
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductByName(String name) {
        try {
            return productRepository.findByPname(name);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    /**
     * Returns products grouped by category in a LinkedHashMap (preserving order).
     */
    public Map<String, List<Product>> getProductsGroupedByCategory() {
        Map<String, List<Product>> grouped = new LinkedHashMap<>();
        List<String> categories = getAllCategories();
        for (String category : categories) {
            grouped.put(category, getProductsByCategory(category));
        }
        return grouped;
    }

    public long count() {
        return productRepository.count();
    }
}
