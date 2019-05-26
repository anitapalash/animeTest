package com.koposovo.animeTest.Service.Abstract;

import com.koposovo.animeTest.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUser(Long userId);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);
}
