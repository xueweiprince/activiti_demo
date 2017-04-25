package com.st.demo.web.util;

public class FilenameUtils {
	
	public static String getFileExtention(String fileName){
		String extention=null;
		int endPoint=fileName.lastIndexOf(".");
		if(endPoint!=-1){
			extention=fileName.substring(endPoint+1);
		}
		return extention;
	}
	
}
