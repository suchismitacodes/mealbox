package com.suchismitacodes.mealbox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.suchismitacodes.mealbox.entity.User;
import com.suchismitacodes.mealbox.repository.UserRepository;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByUemail(email);
    }

    public void updateUser(User user, int id) {
        user.setU_id(id);
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User validateLoginCredentials(String email, String password) {
        for (User user : getAllUser()) {
            if (user.getUemail() != null && user.getUemail().equals(email)
                    && user.getUpassword() != null && user.getUpassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
