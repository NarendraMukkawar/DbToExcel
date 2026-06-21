package com.dbexcel.service;

import com.dbexcel.entity.User;
import com.dbexcel.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
        //return List.of();
    }

    @Override
    public User getUserByUserName(String un) {
        return null;
    }

    @Override
    public  User createUser(User user){
        userRepo.save(user);
        return user;
    }
}
