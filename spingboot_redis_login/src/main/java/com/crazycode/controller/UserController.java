package com.crazycode.controller;

import com.crazycode.pojo.User;
import com.crazycode.service.RedisService;
import com.crazycode.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.TimeUnit;


/**
 * @author Administrator
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;
    @Autowired
    private RedisService redisService;

    @ResponseBody
    @PostMapping("/login")
    public String login(User user) throws Exception {
        String res = "";
        //获取登录用户名
        String uname = user.getUname();
        //用户是否冻结的key的名字
        String freezeKey = "user:" + uname + ":freeze";
        //保存当前用户剩余登录次数的key
        String remainCount = "user:" + uname + ":remainCount";
        //获取登录账号是否冻结的信息
        String freeze = redisService.getCacheKey(freezeKey);
        if (freeze == null) {
            //做登录操作
            User userByName = userService.findUserByName(uname);
            //代表登录失败
            if (userByName == null) {
                //从redis中获取当前用户的剩余登录次数
                String remain_count = redisService.getCacheKey(remainCount);
                //代表第一次登录失败
                if (remain_count == null) {
                    //    设置当前账号在指定有效时间内登录的剩余次数
                    redisService.setCacheKey(remainCount, "2", 24 * 60L);
                    //    不是第一次登录
                } else {
                    //剩余登录次数减一
                    Long count = redisService.descByKey(remainCount);
                    //判断是第二次失败还是第三次失败
                    if (count == 0) {
                        //代表第三次登录失败
                        redisService.setCacheKey(freezeKey, "true", 48 * 60L);
                        //获取冻结剩余时间
                        Long remainTime = redisService.getExpire(freezeKey);
                        res = "当前账号已被冻结,解锁剩余时间为" + remainTime + "分钟";
                        return res;
                    }

                }
                //第一次登录失败和第二次登录失败
                //获取冻结剩余时间
                Long remainTime = redisService.getExpire(remainCount);
                String count = redisService.getCacheKey(remainCount);
                res = "在" + remainTime + "分钟内还有" + count + "次登录次数";
            } else {
                res = "登录成功";
                //删除剩余的登录次数
                redisService.descByKey(remainCount);
            }
        } else {
            //获取冻结剩余时间
            Long remainTime = redisService.getExpire(freezeKey);
            res = "当前账号已被冻结,解锁剩余时间为" + remainTime + "分钟";
        }
        return null;
    }

    @PostMapping("/login2")
    public ModelAndView login2(String uname, String pwd) throws Exception {
        //通过姓名查找用户
        User userByName = userService.findUserByName(uname);
        //得到操作hash的对象
        ValueOperations<String, User> opsForValue = userRedisTemplate.opsForValue();
        if (!opsForValue.getOperations().hasKey(uname)) {
            opsForValue.set(uname, userByName, 24, TimeUnit.HOURS);
        }
        //如果密码相等
        if (userByName.getPwd().equals(pwd)) {
            ModelAndView modelAndView = new ModelAndView("success");
            modelAndView.addObject(userByName);
            return modelAndView;
        }
        return null;
    }

    /**
     * @return
     * @throws Exception
     * @ResponseBody 修饰的方法返回有两种
     * * 对象  自动转换成json字符串并在响应头中告知浏览器以json对象来进行处理
     * * 字符串 默认告知浏览器以text/html来进行处理,所以要告知浏览器以json来处理的话produces = "application/json;charset=utf-8"
     */
    @ResponseBody
    @GetMapping(value = "/findAllUsers", produces = "application/json;charset=utf-8")
    public String findAllUsers() throws Exception {
        return null;
    }
}
