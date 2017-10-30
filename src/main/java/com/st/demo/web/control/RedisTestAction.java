package com.st.demo.web.control;

import com.st.demo.web.dao.IRedisDAO;
import com.st.demo.web.pojo.RedisTestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xuewei on 2017/9/30.
 */
@RequestMapping("redis")
@Controller
public class RedisTestAction {

    @Autowired
    IRedisDAO redisDAO;

    @Autowired
    JdkSerializationRedisSerializer jdkSerializer;

    @RequestMapping("send.do")
    public void sendMessage(){

        String msg = "Hello, Redis!";
        redisDAO.sendMessage("java", msg); //发布字符串消息


        RedisTestBean bean = new RedisTestBean("123456");
        bean.setName("Redis");
        bean.setOld((byte)2);
        bean.setSeliry((short)40);
        bean.setManbers(new String[]{"234567", "3456789"});
//        redisDAO.sendMessage("java", bean); //发布一个普通的javabean消息


        Integer[] values = new Integer[]{21341,123123,12323};
//        redisDAO.sendMessage("java", values);  //发布一个数组消息

    }

}
