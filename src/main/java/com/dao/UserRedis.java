package com.dao;

import com.entity.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Repository
public class UserRedis {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void add(String key, Long time, User user){
        Gson gson = new Gson();
        System.out.println(gson.toJson(user));
        redisTemplate.opsForValue().set(key, gson.toJson(user), time, TimeUnit.MINUTES);
    }

    public User get(String key) {
        Gson gson = new Gson();
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(userJson))
            user = gson.fromJson(userJson, User.class);
        return user;
    }



}
