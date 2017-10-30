package com.st.demo.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.demo.framwork.annotation.PrivilegeAction;
import com.st.demo.framwork.annotation.PrivilegeModel;
import com.st.demo.web.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activiti")
@PrivilegeModel(model = Constants.PRIVILEGE_USER,childmodel = Constants.PRIVILEGE_USER_LOGIN)
public class LoginAction {

	@RequestMapping("loginsuccess.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_USER_LOGIN_SUCCESS)
	public String loginSuccess(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}

	@RequestMapping("test.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_USER_LOGIN_TEST)
	public String test(HttpServletRequest request,HttpServletResponse response){
		return "content2";
	}
	
}
