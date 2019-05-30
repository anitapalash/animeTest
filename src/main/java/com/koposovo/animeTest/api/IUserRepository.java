package com.koposovo.animeTest.api;

import com.koposovo.animeTest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IUserRepository extends CrudRepository <User,Long> {
}

/*
Забить в таблицу

CREATE TABLE if not exists users (
    id bigint PRIMARY KEY,
    userName VARCHAR(25),
    password VARCHAR(20),
    firstName VARCHAR(25),
    lastName VARCHAR(25),
    groupName VARCHAR(10),
    access VARCHAR(7),
    gender VARCHAR(7)
)

select * from users;

insert into users (userName, password, firstName, lastName, groupName, access, gender) VALUES ('root', 'passKopos', 'Koposovo', 'Dev', '17БИ1', 'Admin', 'Женский');

 */
