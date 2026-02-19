package com.suchismitacodes.mealbox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.suchismitacodes.mealbox.dto.AdminLogin;
import com.suchismitacodes.mealbox.entity.Product;
import com.suchismitacodes.mealbox.service.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "Home";
    }

    @GetMapping("/products")
    public String products(Model model) {
        Map<String, List<Product>> groupedProducts = productService.getProductsGroupedByCategory();
        model.addAttribute("groupedProducts", groupedProducts);
        return "Products";
    }

    @GetMapping("/location")
    public String location() {
        return "Locate_us";
    }

    @GetMapping("/about")
    public String about() {
        return "About";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("adminLogin", new AdminLogin());
        return "Login";
    }
}
