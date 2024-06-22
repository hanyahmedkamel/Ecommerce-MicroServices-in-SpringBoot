package com.Almaamouny.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, CustomValue> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, CustomValue> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Use String serializers for keys
        template.setKeySerializer(new StringRedisSerializer());

        // Use JSON serializers for values
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
