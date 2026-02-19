package com.suchismitacodes.mealbox.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suchismitacodes.mealbox.dto.AdminLogin;
import com.suchismitacodes.mealbox.dto.UserLogin;
import com.suchismitacodes.mealbox.entity.Admin;
import com.suchismitacodes.mealbox.entity.Orders;
import com.suchismitacodes.mealbox.entity.Product;
import com.suchismitacodes.mealbox.entity.User;
import com.suchismitacodes.mealbox.service.AdminService;
import com.suchismitacodes.mealbox.service.OrderService;
import com.suchismitacodes.mealbox.service.ProductService;
import com.suchismitacodes.mealbox.service.UserService;
import com.suchismitacodes.mealbox.util.Logic;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminController(UserService userService,
                           AdminService adminService,
                           ProductService productService,
                           OrderService orderService) {
        this.userService = userService;
        this.adminService = adminService;
        this.productService = productService;
        this.orderService = orderService;
    }

    // ─── Authentication ───────────────────────────────────────────────

    @PostMapping("/adminLogin")
    public String adminLogin(@ModelAttribute AdminLogin adminLogin, Model model) {
        Admin admin = adminService.validateAdminCredentials(
                adminLogin.getEmail(), adminLogin.getPassword());
        if (admin != null) {
            return "redirect:/admin/services";
        }
        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute UserLogin userLogin,
                            Model model,
                            HttpSession session) {
        User user = userService.validateLoginCredentials(
                userLogin.getUserEmail(), userLogin.getUserPassword());
        if (user != null) {
            session.setAttribute("loggedUser", user);
            session.setAttribute("loggedEmail", userLogin.getUserEmail());
            List<Orders> orders = orderService.getOrdersForUser(user);
            model.addAttribute("orders", orders);
            model.addAttribute("name", user.getUname());
            model.addAttribute("groupedProducts", productService.getProductsGroupedByCategory());
            return "BuyProduct";
        }
        model.addAttribute("error2", "Invalid email or password");
        return "Login";
    }

    // ─── Product search & order (Customer) ────────────────────────────

    @PostMapping("/product/search")
    public String searchProduct(@RequestParam String productName,
                                Model model,
                                HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        Product product = productService.getProductByName(productName);
        if (product == null) {
            model.addAttribute("message", "SORRY...! Product Unavailable");
            model.addAttribute("product", null);
        } else {
            model.addAttribute("product", product);
        }
        if (user != null) {
            model.addAttribute("orders", orderService.getOrdersForUser(user));
            model.addAttribute("name", user.getUname());
        }
        model.addAttribute("groupedProducts", productService.getProductsGroupedByCategory());
        return "BuyProduct";
    }

    @PostMapping("/product/order")
    public String placeOrder(@ModelAttribute Orders order,
                             Model model,
                             HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        double total = Logic.countTotal(order.getOPrice(), order.getOQuantity());
        order.setTotalAmmout(total);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        orderService.saveOrder(order);
        model.addAttribute("amount", total);
        return "Order_success";
    }

    @GetMapping("/product/back")
    public String back(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user != null) {
            model.addAttribute("orders", orderService.getOrdersForUser(user));
            model.addAttribute("name", user.getUname());
        }
        model.addAttribute("groupedProducts", productService.getProductsGroupedByCategory());
        return "BuyProduct";
    }

    // ─── Admin Dashboard ──────────────────────────────────────────────

    @GetMapping("/admin/services")
    public String adminServices(Model model) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("admins", adminService.getAll());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getOrders());
        return "Admin_Page";
    }

    // ─── Admin CRUD ───────────────────────────────────────────────────

    @GetMapping("/addAdmin")
    public String addAdmin() {
        return "Add_Admin";
    }

    @PostMapping("/addingAdmin")
    public String addingAdmin(@ModelAttribute Admin admin) {
        adminService.addAdmin(admin);
        return "redirect:/admin/services";
    }

    @GetMapping("/updateAdmin/{adminId}")
    public String updateAdmin(@PathVariable int adminId, Model model) {
        Admin admin = adminService.getAdmin(adminId);
        model.addAttribute("admin", admin);
        return "Update_Admin";
    }

    @GetMapping("/updatingAdmin/{id}")
    public String updatingAdmin(@PathVariable int id,
                                @ModelAttribute Admin admin) {
        adminService.update(admin, id);
        return "redirect:/admin/services";
    }

    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable int id) {
        adminService.delete(id);
        return "redirect:/admin/services";
    }

    // ─── Product CRUD (Admin) ─────────────────────────────────────────

    @GetMapping("/addProduct")
    public String addProduct() {
        return "Add_Product";
    }

    @GetMapping("/updateProduct/{productId}")
    public String updateProduct(@PathVariable int productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "Update_Product";
    }

    // ─── User CRUD (Admin) ────────────────────────────────────────────

    @GetMapping("/addUser")
    public String addUser() {
        return "Add_User";
    }

    @GetMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable int userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "Update_User";
    }
}
