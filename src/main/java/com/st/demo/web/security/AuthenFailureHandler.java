package com.st.demo.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import com.st.demo.web.common.SpringContextUtil;
import com.st.demo.web.util.Constants;


public class AuthenFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("---------------------loginFailure---------------------");
		SpringContextUtil.getBean("messageSource");
		request.setAttribute(Constants.LOGIN_FAIL_KEY, Constants.LOGIN_FAIL_MSG);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
