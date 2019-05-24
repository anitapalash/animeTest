package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class UserController {
    @Autowired
    private iJpa repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
