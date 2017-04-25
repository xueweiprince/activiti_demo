package com.st.demo.web.activititype;

import org.activiti.engine.form.AbstractFormType;

public class JavascriptFormType extends AbstractFormType {

	public String getName() {
		// TODO Auto-generated method stub
		return "javascript";
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
		// TODO Auto-generated method stub
		return propertyValue;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
		// TODO Auto-generated method stub
		return (String)modelValue;
	}

}
