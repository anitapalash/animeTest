package com.koposovo.animeTest.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.koposovo.animeTest.api.IUserRepository;
import com.koposovo.animeTest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    private IUserRepository IuserRepository;

    @GetMapping("public/users")
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Iterator<User> iterator = IuserRepository.findAll().iterator();
        while (iterator.hasNext()) {
            users.add(iterator.next());
        }
        return users;
    }

    @GetMapping("/users/{id}")
    public User getUser(Long userId) {
        return IuserRepository.findById(userId).get();
    }

    @PostMapping("/users")
    public User createUser(User user) {
        return IuserRepository.save(user);
    }

    @PostMapping("/users")
    public User updateUser(User user) {
        return IuserRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(Long userId) {
        IuserRepository.deleteById(userId);
    }
}
