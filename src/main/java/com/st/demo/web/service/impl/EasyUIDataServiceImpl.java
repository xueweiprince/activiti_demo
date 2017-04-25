package com.st.demo.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.st.demo.web.dao.IUserDao;
import com.st.demo.web.service.IEasyUIDataService;
import com.st.demo.web.vo.User;

@Service
public class EasyUIDataServiceImpl implements IEasyUIDataService {
	
	@Autowired
	IUserDao userDao;

	@Override
	public User getSigleUser(int id) {
		// TODO Auto-generated method stub
		User user=userDao.getUserById(id);
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
