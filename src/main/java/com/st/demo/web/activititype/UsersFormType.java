package com.st.demo.web.activititype;

import java.util.Arrays;

import org.activiti.engine.form.AbstractFormType;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class UsersFormType extends AbstractFormType {

	public String getName() {
		// TODO Auto-generated method stub
		return "users";
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValues) {
		// TODO Auto-generated method stub
		String split[]=StringUtils.split(propertyValues, ",");
		//String split[]=propertyValues.split(",");
		return Arrays.asList(split);
	}

	@Override
	public String convertModelValueToFormValue(Object modelValues) {
		// TODO Auto-generated method stub
		return ObjectUtils.toString(modelValues);
	}

}
