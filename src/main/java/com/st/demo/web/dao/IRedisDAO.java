package com.st.demo.web.dao;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * Created by xuewei on 2017/9/30.
 */
public interface IRedisDAO {

    public void sendMessage(String channel, Object message);

    public RedisTemplate getRedisTemplate();

    public void setRedisTemplate(RedisTemplate redisTemplate);
}
