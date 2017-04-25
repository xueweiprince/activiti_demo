package com.st.demo.web.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import com.st.demo.web.dao.RedisUserDao;
import com.st.demo.web.vo.RedisUser;


public class RedisUserDaoImpl implements RedisUserDao {
	
	 @Autowired
	 protected RedisTemplate<Serializable, Serializable> redisTemplate;



	@Override
	public void saveUser(final RedisUser user) {
		redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
                               redisTemplate.getStringSerializer().serialize(user.getName()));
                return null;
            }
        });
		
	}

	@Override
	public RedisUser getUser(final long id) {
		return redisTemplate.execute(new RedisCallback<RedisUser>() {
            @Override
            public RedisUser doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
                if (connection.exists(key)) {
                    byte[] value = connection.get(key);
                    String name = redisTemplate.getStringSerializer().deserialize(value);
                    RedisUser user = new RedisUser();
                    user.setName(name);
                    user.setId(id);
                    return user;
                }
                return null;
            }
        });

	}

}
