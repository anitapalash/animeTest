package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserController {
    private iJpa repo;

    @Bean
    public UserController getUserController() {
        return new UserController();
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User findById(Long id) {
        return repo.findById(id).get();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
