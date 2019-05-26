package com.koposovo.animeTest.model;

import com.koposovo.animeTest.api.Access;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;        //имя

    @Column
    private String userName;       //логин

    @Column
    private String password;    //пароль

    @Column
    private String lastName;     //фамилия

    @Column
    private String group;       //группа

    @Column
    private Access access;      //уровень доступа

    @Column
    private String gender;      //пол


    public User() { this.access = Access.USER; }

    public User(String userName, String password)
    {   this.userName = userName;
        this.password = password;
        this.access = Access.USER; }

    public User(String firstName, String userName, String password, String lastName, String group, String gender) {
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.group = group;
        this.gender = gender;
        this.access = Access.USER;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public Access getAccess() {
        return access;
    }
    public void setAccess(Access access) {
        this.access = access;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
