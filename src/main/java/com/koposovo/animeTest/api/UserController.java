package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UserController {
    @Autowired
    private iJpa repo;


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
