package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserController {
    @Autowired
    private iJpa repo;

    public void save(User user) {
        repo.save(user);
    }

    public User getUser(Long id) {
        return repo.findById(id).get();
    }
}
