package com.koposovo.animeTest.api;

import com.koposovo.animeTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserController {
    @Autowired
    private IUserRepository IuserRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Iterator<User> iterator = IuserRepository.findAll().iterator();
        while (iterator.hasNext()) {
            users.add(iterator.next());
        }

        return users;
    }

    public User getUser(Long userId) {
        return IuserRepository.findById(userId).get();
    }

    public User createUser(User user) {
        return IuserRepository.save(user);
    }

    public User updateUser(User user) {
        return IuserRepository.save(user);
    }

    public void deleteUser(Long userId) {
        IuserRepository.deleteById(userId);
    }
}
