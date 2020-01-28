package com.nicolrom.services.impl;

import com.nicolrom.dao.UserDao;
import com.nicolrom.entities.User;
import com.nicolrom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        List<User> users = userDao.getAllUsers();
        return users;
    }

}
