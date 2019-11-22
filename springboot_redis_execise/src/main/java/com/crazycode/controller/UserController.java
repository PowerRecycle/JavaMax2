package com.crazycode.controller;

import com.crazycode.pojo.User;
import com.crazycode.service.RedisService;
import com.crazycode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public String login(User user) throws Exception {
        String res = "";//保存输出的信息
        String username = user.getName();//获取登录的用户名
        String freezeKey = "user:" + username + ":freeze";//用户是否冻结的key的名字
        String remainCount = "user:" + username + ":remainCount";//保存当前用户剩余登陆次数的key
        //控制逻辑
        String freeze = redisService.getcacheKey(freezeKey);//获取登录账号是否冻结的信息
        //没有冻结
        if (freeze == null) {
            //做登录操作
            user = userService.login(user);//调用登录业务操作数据库
            //登录失败
            if (user == null) {
                String remain_count = redisService.getcacheKey(remainCount);//从redis中获取当前用户剩余登陆次数
                //第一次登陆失败
                if (remain_count == null) {
                    //设置当前账号在指定有效时间内登陆的剩余次数
                    redisService.setcacheKey(remainCount, "2", 60l * 24);
                } else {//不是第一次登陆
                    Long count = redisService.desccacheByKey(remainCount);//剩余登陆次数减一
                    //判断是第二次失败还是第三次失败
                    if (count == 0) {
                        //代表第三次登陆失败
                        redisService.setcacheKey(freezeKey, "true", 60l * 48);
                        Long remainTime = redisService.getUserExpire(freezeKey);
                        res = "当前账号已经被冻结,解锁剩余时间为" + remainTime + "分钟";
                        return res;
                    }
                }
                //第一次登陆失败和第二次登陆失败
                Long remainTime = redisService.getUserExpire(remainCount);
                String count = redisService.getcacheKey(remainCount);
                res = "在" + remainTime + "分钟内还有" + count + "登陆次数";
            } else {
                res = "登陆成功!";
                redisService.delCacheKey(remainCount);// 删除剩余的登陆次数
            }

        } else {
            //获取账号冻结剩余时间
            Long remainTime = redisService.getUserExpire(freezeKey);
            res = "当前账号已经被冻结,解锁剩余时间为" + remainTime + "分钟";
        }

        return res;
    }
}
