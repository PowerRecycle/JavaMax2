package com.crazycode.service;

import com.crazycode.pojo.User;

import java.util.List;

/**
 * UserService逻辑层实现类
 *
 * @author Administrator
 */
public interface UserService {
    /**
     * 查询所有新闻
     *
     * @return
     * @throws Exception
     */
    List<User> findAllUsers() throws Exception;

    /**
     * 通过名字查找用户
     *
     * @return
     * @throws Exception
     */
    User findUserByName(String name) throws Exception;
}
