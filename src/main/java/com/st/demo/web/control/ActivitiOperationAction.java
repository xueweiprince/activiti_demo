package com.st.demo.web.control;

import com.st.demo.framwork.annotation.PrivilegeAction;
import com.st.demo.framwork.annotation.PrivilegeModel;
import com.st.demo.web.util.Constants;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/activiti/operation")
@PrivilegeModel(model = Constants.PRVIVLEGE_ACTIVITI,childmodel = Constants.PRIVILEGE_ACTIVITI_DEPLOYMENT)
public class ActivitiOperationAction {
	
	@Autowired
	RepositoryService repositoryService;
	
	@RequestMapping(value="del")
	@PrivilegeAction(action = Constants.PRIVILEGE_ACTIVITI_DODEL)
	public String delProcess(String deploymentId){
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:/activitiList.jsp";
	}

}
