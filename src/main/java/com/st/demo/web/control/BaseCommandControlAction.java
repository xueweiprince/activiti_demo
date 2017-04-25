package com.st.demo.web.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.BaseCommandController;
import com.st.demo.web.vo.HelloWorld;

public class BaseCommandControlAction extends BaseCommandController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Object command=getCommand(req);
		ServletRequestDataBinder binder=bindAndValidate(req, command);
		
		HelloWorld helloWorld=(HelloWorld)command;
		Map modle=new HashMap();
		modle.put("helloWrold", helloWorld.getTitle());
		return new ModelAndView("result",modle);
	}

}
