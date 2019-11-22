package com.crazycode.service.impl;

import com.crazycode.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // private ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

    /*public RedisServiceImpl() {
        opsForValue = stringRedisTemplate.opsForValue();
    }*/

    /**
     * 操作字符串String类型的key
     *
     * @param key
     * @param value
     * @param timeout
     * @throws Exception
     */
    @Override
    public void setCacheKey(String key, String value, Long timeout) throws Exception {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        if (timeout == null) {
            opsForValue.set(key, value);
        } else {
            opsForValue.set(key, value, timeout, TimeUnit.MINUTES);
        }
    }

    /**
     * 获取key指定的值
     *
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public String getCacheKey(String key) throws Exception {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    /**
     * 对指定key中的值进行减一操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public Long descByKey(String key) throws Exception {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.decrement(key);
    }

    @Override
    public Long getExpire(String key) throws Exception {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.getOperations().getExpire(key, TimeUnit.MINUTES);
    }

    @Override
    public void delCacheKey(String key) throws Exception {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.getOperations().delete(key);
    }
}
