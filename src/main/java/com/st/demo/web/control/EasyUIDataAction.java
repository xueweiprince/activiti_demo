package com.st.demo.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.st.demo.web.service.IEasyUIDataService;
import com.st.demo.web.vo.User;

@Controller
@RequestMapping(value="/activiti/process")
public class EasyUIDataAction {
	
	@Autowired
	IEasyUIDataService easyUIDataService;
	
	@RequestMapping(value="/singleuser")
	@ResponseBody
	public ModelAndView findUser(){
		
		User user=easyUIDataService.getSigleUser(1);
		ModelAndView mav=new ModelAndView("easyuilist");
		mav.addObject(user);
		return mav;
	}
	
	@RequestMapping(value="/forward")
	public ModelAndView forward(String url){
		if(url==null||url.equals("")){
			return new ModelAndView("easyuilist");
		}
		return new ModelAndView(url);
	}
}
