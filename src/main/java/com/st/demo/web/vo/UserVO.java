package com.st.demo.web.vo;

public class UserVO {
	
	private String userName;
	private String password;
	private int enable;
	private String email;
	
	public UserVO(){}
	
	public UserVO(String userName,String password,int enable,String email){
		this.userName=userName;
		this.password=password;
		this.enable=enable;
		this.email=email;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
