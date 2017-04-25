package com.st.demo.web.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.st.demo.web.vo.HelloWorld;

public class SimpleFormControlAction extends SimpleFormController {
	
	public ModelAndView onSubmit(Object command){
		
		HelloWorld helloWorld=(HelloWorld)command;
		Map model=new HashMap();
		model.put("helloWorld", helloWorld.getTitle());
		return new ModelAndView("result",model);
	}

}
