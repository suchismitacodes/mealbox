package com.suchismitacodes.mealbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.suchismitacodes.mealbox.entity.Product;
import com.suchismitacodes.mealbox.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addingProduct")
    public String addingProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/admin/services";
    }

    @GetMapping("/updatingProduct/{productId}")
    public String updatingProduct(@PathVariable int productId,
                                  @ModelAttribute Product product) {
        productService.updateProduct(product, productId);
        return "redirect:/admin/services";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin/services";
    }
}
