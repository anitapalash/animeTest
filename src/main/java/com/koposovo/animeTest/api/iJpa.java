package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface iJpa extends CrudRepository<User, Long> {
    List<User> findAll();

    User save(User s);

    Optional<User> findById(Long id);

    void deleteById(Long aLong);
}
