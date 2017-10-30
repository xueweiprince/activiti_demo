package com.st.demo.web.control;

import com.st.demo.framwork.annotation.PrivilegeAction;
import com.st.demo.framwork.annotation.PrivilegeModel;
import com.st.demo.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.st.demo.web.service.ISystemAcountService;
import com.st.demo.web.vo.UserVO;

@Controller
@RequestMapping("activiti/systemacount")
@PrivilegeModel(model = Constants.PRIVILEGE_USER,childmodel = Constants.PRIVILEGE_USER_REGIST)
public class SystemAcountAction {
	
	@Autowired
	ISystemAcountService systemAcountService;
	
	@RequestMapping("forward.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_USER_REGIST_FORWARD)
	public String toRegistPage(){
		return "regist";
	}
	
	@RequestMapping("regist.do")
	@PrivilegeAction(action = Constants.PRIVILEGE_USER_REGIST_ADD)
	public void regist(String username,String password){
		
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		String encryptionpassword=md5.encodePassword(password, username);
		
		UserVO user=new UserVO(username,encryptionpassword,1,"");
		
		systemAcountService.addSystemAcount(user);
		
	}

}
