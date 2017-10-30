package com.st.demo.framwork.listener;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by xuewei on 2017/9/30.
 */
public class MessageDelegateListenerImpl implements MessageListener {

    @Autowired
    JdkSerializationRedisSerializer jdkSerializer;

    @Autowired
    RedisTemplate redisTemplate;

    RedisSerializer serializer;

    public void handleMessage(Serializable message) {
        //什么都不做,只输出
        if(message == null){
            System.out.println("null");
        } else if(message.getClass().isArray()){
            System.out.println(Arrays.toString((Object[])message));
        } else if(message instanceof List<?>) {
            System.out.println(message);
        } else if(message instanceof Map<? , ?>) {
            System.out.println(message);
        } else {
            System.out.println(ToStringBuilder.reflectionToString(message));
        }
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        serializer = redisTemplate.getValueSerializer();
        String messageStr = serializer.deserialize(message.getBody()).toString();
//        String messageStr = jdkSerializer.deserialize(message.getBody()).toString();
        System.out.println("message received:" + messageStr);
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
