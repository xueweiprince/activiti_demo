package com.st.demo.framwork.listener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.st.demo.framwork.annotation.PrivilegeAction;
import com.st.demo.framwork.annotation.PrivilegeModel;
import com.st.demo.framwork.vo.PrivilegeResource;
import org.springframework.web.bind.annotation.RequestMapping;

public class PrivilegeInitListener implements ServletContextListener {

	/*
	 * 扫action下的所有类
	 */
	private List<Class> getClasses(String pckgname) {

		ArrayList<Class> classes = new ArrayList<Class>();

		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		String packagePath = pckgname.replace(".", "/");
		URL url = cld.getResource(packagePath);
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(url);
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				File file = new File(url.getPath());
//				File[] childFiles = file.listFiles();
				List<File>files=getFiles(file);
				for (File childFile : files) {
					String childFilePath = childFile.getPath();
					if (childFilePath.endsWith(".class")) {
						childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
								childFilePath.lastIndexOf(".class"));
						childFilePath = childFilePath.replace("\\", ".");
						try {
							classes.add(Class.forName(childFilePath));

						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return classes;
	}
	
	//递归获取所有类文件
	List<File>files=new ArrayList();
	private List<File> getFiles(File file){
		
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if(childFile.isDirectory()){
				getFiles(childFile);
			}else{
				files.add(childFile);
			}
			
		}
		return files;
	}

	/*
	 * 初始化权限资源
	 */
	private void initPrivilege(List<Class> classes) {
		List<PrivilegeResource> resources = new ArrayList();
		for (Class clazz : classes) {
			String className = clazz.getName();
			String modelName = className.substring(className.lastIndexOf(".") + 1);//获取action的名字
			String childModelName=className.substring(className.lastIndexOf(".") + 1);
			Method[] methods = clazz.getDeclaredMethods();//获取注解的方法

			RequestMapping rootRequestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);//获取action
			PrivilegeModel privilegeModel = (PrivilegeModel) clazz.getAnnotation(PrivilegeModel.class);//获取注解的模块名

			if (privilegeModel != null) {
				modelName = privilegeModel.model();
				childModelName=privilegeModel.childmodel();
			}

			for (Method method : methods) {
				RequestMapping requestMapping = null;
				PrivilegeAction privilegeAction = null;
				if ((requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class)) != null
						&& (privilegeAction = (PrivilegeAction) method.getAnnotation(PrivilegeAction.class)) != null) {
					String childrenPath[] = requestMapping.value();
					String action = privilegeAction.action().toString();
					if (rootRequestMapping != null) {
						String rootPath[] = rootRequestMapping.value();
						for (String root : rootPath) {
							if(!root.subSequence(0, 1).equals("/")){
								root="/"+root;
							}
							for (String childPath : childrenPath) {
								if(!childPath.substring(0,1).equals("/")){
									childPath="/"+childPath;
								}
								if(childPath.lastIndexOf(".")==-1){
									childPath=childPath+".do";
								}
								String fullPath = root + childPath;
								PrivilegeResource privilegeResource = new PrivilegeResource(
										UUID.randomUUID().toString(), modelName, childModelName, className, action, fullPath);
								resources.add(privilegeResource);
							}
						}
					} else {
						for (String childPath : childrenPath) {
							String fullPath = childPath + ".do";
							PrivilegeResource privilegeResource = new PrivilegeResource(UUID.randomUUID().toString(),
									modelName, childModelName, className, action, fullPath);
							resources.add(privilegeResource);
						}
					}
				}
			}
		}

		initData(resources);//权限资源入库

	}
	
	/**
	 * 初始化权限资源
	 * @param resources
	 */
	private void initData(List<PrivilegeResource>resources){
		Connection con = null;
		PreparedStatement pre = null;
		Statement stm=null;
		ResultSet resultset = null;
		
		Properties prop = new Properties();
		InputStream in = getClass() .getResourceAsStream( "/jdbc.properties" );

		try {
			prop.load(in);
			
			String dirver=prop.getProperty("jdbc.driver");
			String url=prop.getProperty("jdbc.url");
			String user=prop.getProperty("jdbc.user");
			String password=prop.getProperty("jdbc.password");
			
			Class.forName(dirver);
			System.out.println("开始连接");
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("连接成功");
			
			String searchsql="select * from t_resources";
			pre=con.prepareStatement(searchsql);
			resultset=pre.executeQuery();//查出所有已存在的权限资源
			List<String>urlList=new ArrayList();
			while(resultset.next()){
				urlList.add(resultset.getString("url"));
			}
			List<PrivilegeResource>resourceList=new ArrayList();
			for(PrivilegeResource pr:resources){
				boolean flag=false;
//				while(resultset.next()){
//					String className=resultset.getString("classname");
//					String modelName=resultset.getString("modelname");
//					String action=resultset.getString("action");
//					if(pr.getAction().equals(action)&&pr.getClassName().equals(className)&&pr.getModelName().equals(modelName)){
//						flag=true;
//						break;
//					}
//				}
				for(String url_:urlList){
					if(url_.equals(pr.getUrl())){
						flag=true;
						break;
					}
				}
				if(!flag){
					System.out.println(pr.getUrl());
					resourceList.add(pr);
				}
			}
			
			if(resourceList!=null&&resourceList.size()!=0){
				//String del = "delete from T_RESOURCE_INFO";
				StringBuffer insert=new StringBuffer("insert into t_resources(CLASSNAME,modlename,childmodlename,actionname,URL,CREATETIME,UPDATETIME) values ");
				boolean flag=false;
				for(PrivilegeResource privilegeResource:resourceList){
					insert.append("('"+privilegeResource.getClassName()+"',").append("'"+privilegeResource.getModelName()+"',").append("'"+privilegeResource.getChildModel()+"',").append("'"+privilegeResource.getAction()+"',").append("'"+privilegeResource.getUrl()+"',").append("now(),now()),");
				}
				insert.delete(insert.length()-1,insert.length());
				stm=con.createStatement();
				//stm.addBatch(del);
				stm.addBatch(insert.toString());
				//pre = con.prepareStatement(del);// 删除库中已有权限资源
				stm.executeBatch();
				con.commit();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			try {
				con.rollback();
				con.commit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("-----------------------连接失败-----------------------");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭jdbc
				if (resultset != null)
					resultset.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				if(stm !=null)
					stm.close();
				System.out.println("数据库连接已关闭");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		List<Class> classes = getClasses("com.st.demo.web.control");//获取所有控制层的类
		this.initPrivilege(classes);
	}

}
