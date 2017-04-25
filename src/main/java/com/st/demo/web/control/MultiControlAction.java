package com.st.demo.web.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class MultiControlAction extends MultiActionController {
	
	@Autowired
	ProcessEngineFactoryBean processEngineFactory;

	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {

		try {
			ProcessEngine processEngine=processEngineFactory.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map model = new HashMap();
		model.put("excute", "you excute insert method!");
		return new ModelAndView("result", model);
	}

	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {

		Map model = new HashMap();
		model.put("excute", "you excute update method!");
		return new ModelAndView("result", model);
	}

	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {

		Map model = new HashMap();
		model.put("excute", "you excute delete method!");
		return new ModelAndView("result", model);
	}

}
