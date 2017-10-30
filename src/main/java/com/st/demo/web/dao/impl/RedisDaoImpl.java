package com.st.demo.web.dao.impl;

import com.st.demo.web.dao.IRedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by xuewei on 2017/9/30.
 */
@Repository("redisDAO")
public class RedisDaoImpl implements IRedisDAO{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void sendMessage(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    @Override
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
