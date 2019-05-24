package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface iJpa extends Repository<User, Long> {
    List<User> findAll();

    void save(User s);

    Optional<User> findById(Long id);

    void deleteById(Long aLong);
}
