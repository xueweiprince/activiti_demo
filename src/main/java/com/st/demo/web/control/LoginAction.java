package com.st.demo.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("activiti")
public class LoginAction {

	@RequestMapping("loginsuccess.do")
	public String loginSuccess(HttpServletRequest request,HttpServletResponse response){
		return "/index";
	}
	
}
