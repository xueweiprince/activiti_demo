package com.st.demo.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestAction implements Controller {

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("----------------------");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("index.jsp");
		
		return modelAndView;
	}

}
