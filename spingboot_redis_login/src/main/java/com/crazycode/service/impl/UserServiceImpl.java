package com.crazycode.service.impl;

import com.crazycode.mapper.UserMapper;
import com.crazycode.pojo.User;
import com.crazycode.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有新闻
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<User> findAllUsers() throws Exception {
        return userMapper.findAllUsers();
    }

    /**
     * 通过名字查找用户
     *
     * @return
     * @throws Exception
     */
    @Override
    public User findUserByName(String name) throws Exception {
        return userMapper.findUserByName(name);
    }
}
