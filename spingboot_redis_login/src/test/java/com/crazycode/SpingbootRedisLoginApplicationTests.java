package com.crazycode;

import com.crazycode.pojo.User;
import com.crazycode.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpingbootRedisLoginApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() throws Exception {
        /*List<User> allUsers = userService.findAllUsers();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }*/

        System.out.println(userService.findUserByName("赵煜东"));
    }

}
