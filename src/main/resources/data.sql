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
