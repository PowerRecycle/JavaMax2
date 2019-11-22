package com.crazycode.service.impl;

import com.crazycode.mapper.UserMapper;
import com.crazycode.pojo.User;
import com.crazycode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) throws Exception {
        return userMapper.queryUser(user);
    }
}
