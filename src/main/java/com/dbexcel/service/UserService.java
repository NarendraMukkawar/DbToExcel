package com.dbexcel.service;

import com.dbexcel.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByUserName(String un);
    User createUser(User user);
}
