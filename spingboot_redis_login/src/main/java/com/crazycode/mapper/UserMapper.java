package com.crazycode.mapper;

import com.crazycode.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户mapper
 *
 * @author Administrator
 */
@Component
@Mapper
public interface UserMapper {
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
     * @param name
     * @return
     * @throws Exception
     */
    User findUserByName(String name) throws Exception;


}
