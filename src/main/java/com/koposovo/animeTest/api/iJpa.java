package com.koposovo.animeTest.api;

import com.koposovo.animeTest.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iJpa extends CrudRepository<User, Long> {
}
