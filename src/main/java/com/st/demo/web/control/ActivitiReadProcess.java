package com.st.demo.web.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.demo.framwork.annotation.PrivilegeAction;
import com.st.demo.framwork.annotation.PrivilegeModel;
import com.st.demo.web.util.Constants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONSerializer;
import com.st.demo.web.vo.ProcessVO;

@Controller
@RequestMapping(value="/activiti/process")
@PrivilegeModel(model = Constants.PRVIVLEGE_ACTIVITI,childmodel = Constants.PRIVILEGE_ACTIVITI_DEPLOYMENT)
public class ActivitiReadProcess {
	
	@Autowired
	RepositoryService repositoryService;
	
	/**
	 * 读取一个流程图的xml，或者它的图片
	 * @param processDefintionId
	 * @param resourceName
	 * @param response
	 */
	@RequestMapping(value="read.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_ACTIVITI_READ)
	public void readResource(@RequestParam("pid")String processDefintionId,@RequestParam("resourceName")String resourceName,HttpServletResponse response){
		ProcessDefinitionQuery pdq=repositoryService.createProcessDefinitionQuery();
		ProcessDefinition pd=pdq.processDefinitionId(processDefintionId).singleResult();
		InputStream resourceAsStream=repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);
		byte[]b=new byte[1024];
		int len=-1;
		try {
			while((len=resourceAsStream.read(b, 0, 1024))!=-1){
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="list.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_ACTIVITI_LIST)
	@ResponseBody
	public List<Map<String,String>> listProcess(){
		List<ProcessDefinition> definitionList=repositoryService.createProcessDefinitionQuery().listPage(0, 2);
		List<Map<String,String>>list=new ArrayList();

		for(ProcessDefinition process:definitionList){
			Map<String,String> map=new HashMap();
			map.put("id",process.getId());
			map.put("key",process.getKey());
			map.put("version",process.getVersion()+"");
			map.put("name",process.getName());
			map.put("resourceName",process.getResourceName());
			map.put("deploymentId",process.getDeploymentId());
			map.put("descrip",process.getDescription());
			map.put("diagramName",process.getDiagramResourceName());
			list.add(map);
		}
//		ModelAndView mdv=new ModelAndView("activiti/activitiList");
//		mdv.addObject("definitionList", definitionList);
		return list;
	}
	
	@RequestMapping(value="list2.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_ACTIVITI_LIST2)
	public void listProcess2(HttpServletRequest request,HttpServletResponse response){
		List<ProcessDefinition> definitionList=repositoryService.createProcessDefinitionQuery().list();
		List<ProcessVO>processList=new ArrayList();
		for(ProcessDefinition processDef:definitionList){
			ProcessVO process=new ProcessVO();
			process.setDeploymentId(processDef.getDeploymentId());
			process.setDescription(processDef.getDescription());
			process.setDiagramResourceName(processDef.getDiagramResourceName());
			process.setId(processDef.getId());
			process.setKey(processDef.getKey());
			process.setName(processDef.getName());
			process.setResourceName(processDef.getResourceName());
			process.setTenantId(processDef.getTenantId());
			process.setVrsion(processDef.getVersion());
			processList.add(process);
		}
		String s=JSONSerializer.toJSON(processList).toString();
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
