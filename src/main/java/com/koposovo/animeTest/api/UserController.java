package com.koposovo.animeTest.api;

import com.koposovo.animeTest.Service.UserService;
import com.koposovo.animeTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/users")
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}
