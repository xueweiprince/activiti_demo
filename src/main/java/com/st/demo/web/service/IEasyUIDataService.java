package com.st.demo.web.service;

import java.util.List;

import com.st.demo.web.vo.User;

public interface IEasyUIDataService {
	
	public User getSigleUser(int id);
	
	public List<User> getUsers();

}
