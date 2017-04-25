package com.st.demo.web.dao;

import com.st.demo.web.vo.RedisUser;

public interface RedisUserDao {
	public void saveUser(final RedisUser user);
	
	public RedisUser getUser(final long id);
}
