package com.crazycode.mapper;

import com.crazycode.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
/*@Mapper*/
public interface UserMapper {
    @Select("select id,name,pwd,email from users where name=#{name} and pwd=#{pwd}")
    public User queryUser(User user) throws Exception;
}
