package com.neusoft.xml.builder;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassXMLAnnotation {
	/**
	 * set node to be root
	 * @return
	 */
	public boolean root() default false;
	/**
	 * node's name
	 * @return
	 */
	public String name();
	/**
	 * parent's name
	 * @return
	 */
	public String parent() default "";
}
