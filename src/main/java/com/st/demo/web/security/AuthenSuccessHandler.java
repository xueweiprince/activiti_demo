package com.st.demo.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.st.demo.web.util.Constants;

public class AuthenSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("----------------authSuccess----------------------");
		request.removeAttribute(Constants.LOGIN_FAIL_KEY);
		request.getRequestDispatcher("activiti/loginsuccess.do").forward(request, response);

	}

}
