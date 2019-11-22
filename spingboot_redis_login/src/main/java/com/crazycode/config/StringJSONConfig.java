package com.crazycode.config;

import com.crazycode.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Controller;

import java.net.UnknownHostException;

@Controller
public class StringJSONConfig {

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用指定的序列化器来序列化指定的数据类型
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        return template;
    }
}
