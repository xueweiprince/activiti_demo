package com.st.demo.framwork.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeModel {
	/**
	 * 注解的成员以无参的方法的形式被声明
	 * 其方法名和返回值定义了该成员的名字和类型
	 * @return
	 */
	String model() default "";
	String childmodel();
	
}
