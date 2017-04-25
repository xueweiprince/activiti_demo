package com.st.demo.web.dao;

import com.st.demo.web.vo.User;

import java.util.List;

public interface IUserDao {
	public User getUserById(int id);
	
	public List<User> getUsers();
}
