package com.koposovo.animeTest.api;

import com.koposovo.animeTest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IUserRepository extends CrudRepository <User,Long> {

}