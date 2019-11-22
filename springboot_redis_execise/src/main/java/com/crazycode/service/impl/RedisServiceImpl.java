package com.crazycode.service.impl;

import com.crazycode.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //private ValueOperations<String, String> ops;

   /* public RedisServiceImpl() {
        ops = stringRedisTemplate.opsForValue();
    }
*/
    @Override
    public void setcacheKey(String key, String value, Long timeout) throws Exception {
        if (timeout == null) {
            stringRedisTemplate.opsForValue().set(key,value);
        }else{
            stringRedisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);
        }

    }

    @Override
    public String getcacheKey(String key) throws Exception {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long desccacheByKey(String key) throws Exception {
        return stringRedisTemplate.opsForValue().decrement(key);
    }

    @Override
    public Long getUserExpire(String key) throws Exception {
        return stringRedisTemplate.opsForValue().getOperations().getExpire(key,TimeUnit.MINUTES);
    }

    @Override
    public void delCacheKey(String key) throws Exception {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}
