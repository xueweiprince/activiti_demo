package com.st.demo.web.util;

public class Constants {
	//key
	public static final String LOGIN_FAIL_KEY="loginfail";
	
	//message
	public static final String LOGIN_FAIL_MSG="username or password invalidate!";


	//权限大模块名称
	public static final String PRIVILEGE_USER="用户管理";
	public static final String PRVIVLEGE_ACTIVITI="流程管理";

	//权眼小模块名称
	public static final String PRIVILEGE_USER_REGIST="用户注册";
	public static final String PRIVILEGE_USER_LOGIN="用户登陆";
	public static final String PRIVILEGE_ACTIVITI_DEPLOYMENT="流程部署";

	//权限方法名称
	public static final String PRIVILEGE_USER_REGIST_FORWARD="转向注册页";
	public static final String PRIVILEGE_USER_REGIST_ADD="注册";
	public static final String PRIVILEGE_USER_LOGIN_SUCCESS="转向登陆成功页";
	public static final String PRIVILEGE_USER_LOGIN_TEST="转向测试页";

	public static final String PRIVILEGE_ACTIVITI_DODEPLOY="部署";
	public static final String PRIVILEGE_ACTIVITI_DODEL="删除";
	public static final String PRIVILEGE_ACTIVITI_READ="读取";
	public static final String PRIVILEGE_ACTIVITI_LIST="列表";
	public static final String PRIVILEGE_ACTIVITI_LIST2="列表2";

}
