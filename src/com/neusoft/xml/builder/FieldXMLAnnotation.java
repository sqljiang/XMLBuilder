package com.neusoft.xml.builder;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldXMLAnnotation{
	/**
	 * node's type
	 * @return
	 */
	public Type type() default Type.ELEMENT;
	/**
	 * node's name
	 * @return
	 */
	public String name();
	/**
	 * child's name
	 * 
	 * this is used to array or collection which contain's element type is primitive type.
	 * 
	 * for example :
	 * 
	 *  int[] week = {1,2,3,4,5,6,7};
	 *  
	 *  List<String> week = new ArrayList<String>();
	 *  week.add("Monday");
	 *  week.add("Tuesday");
	 *  week.add("Wednesday");
	 *  week.add("Thursday");
	 *  week.add("Friday");
	 * 
	 * @return
	 */
	public String child()default "";
}
