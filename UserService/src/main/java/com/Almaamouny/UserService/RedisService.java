package com.Almaamouny.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, CustomValue> redisTemplate;

    public void saveData(String key, CustomValue value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public CustomValue getData(String key) {
        CustomValue customValue=redisTemplate.opsForValue().get(key);
        return customValue;
    }

    public void deleteData(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }
}
