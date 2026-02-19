package com.suchismitacodes.mealbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.suchismitacodes.mealbox.entity.User;
import com.suchismitacodes.mealbox.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addingUser")
    public String addingUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/admin/services";
    }

    @GetMapping("/updatingUser/{id}")
    public String updatingUser(@PathVariable int id,
                               @ModelAttribute User user) {
        userService.updateUser(user, id);
        return "redirect:/admin/services";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/services";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
}
