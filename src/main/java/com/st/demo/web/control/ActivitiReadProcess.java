package com.st.demo.web.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONSerializer;
import com.st.demo.web.vo.ProcessVO;

@Controller
@RequestMapping(value="/activiti/process")
public class ActivitiReadProcess {
	
	@Autowired
	RepositoryService repositoryService;
	
	/**
	 * 读取一个流程图的xml，或者它的图片
	 * @param processDefintionId
	 * @param resourceName
	 * @param response
	 */
	@RequestMapping(value="read")
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
	
	@RequestMapping(value="list")
	public ModelAndView listProcess(){
		List<ProcessDefinition> definitionList=repositoryService.createProcessDefinitionQuery().listPage(2, 1);
		ModelAndView mdv=new ModelAndView("activiti/activitiList");
		mdv.addObject("definitionList", definitionList);
		return mdv;
	}
	
	@RequestMapping(value="list2")
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
