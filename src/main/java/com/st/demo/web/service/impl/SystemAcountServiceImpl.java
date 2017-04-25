package com.st.demo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.st.demo.web.dao.ISystemAcountDao;
import com.st.demo.web.service.ISystemAcountService;
import com.st.demo.web.vo.UserVO;

@Service
public class SystemAcountServiceImpl implements ISystemAcountService {
	
	@Autowired
	ISystemAcountDao systemAcountDao;

	@Override
	public void addSystemAcount(UserVO user) {
		// TODO Auto-generated method stub
		systemAcountDao.addAcount(user);
	}

}
