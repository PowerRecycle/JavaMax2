package com.crazycode.service;

import com.crazycode.pojo.User;

public interface UserService {
    /**
     * 登录功能
     * @param user
     * @return
     * @throws Exception
     */
    public User login(User user) throws Exception;
}
