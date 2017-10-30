package com.st.demo.framwork.vo;

public class PrivilegeResource {
	
	/**
	 * ����ID
	 */
	public String id;
	/**
	 * ģ����
	 */
	public String modelName;
	
	/**
	 * 子模块
	 */
	public String childModel;
	/**
	 * ����
	 */
	public String className;
	/**
	 * ����
	 */
	public String action;
	/**
	 * ����·��
	 */
	public String url;
	/**
	 * ������
	 */
	public String creator;
	/**
	 * �޸���
	 */
	public String updator;
	
	public PrivilegeResource(String id,String modelName,String childModel,String className,String action,String url){
		this.id=id;
		this.modelName=modelName;
		this.childModel=childModel;
		this.className=className;
		this.action=action;
		this.url=url;
	}
	
	public PrivilegeResource(){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public String getChildModel() {
		return childModel;
	}

	public void setChildModel(String childModel) {
		this.childModel = childModel;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}

}
