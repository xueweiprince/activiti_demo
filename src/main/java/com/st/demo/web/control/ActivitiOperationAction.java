package com.st.demo.web.control;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/activiti/operation")
public class ActivitiOperationAction {
	
	@Autowired
	RepositoryService repositoryService;
	
	@RequestMapping(value="del")
	public String delProcess(String deploymentId){
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:/activitiList.jsp";
	}

}
