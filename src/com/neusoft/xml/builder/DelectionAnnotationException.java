package com.neusoft.xml.builder;

/**
 * 
 * the annotation not exist exception
 * 
 * @author
 */
@SuppressWarnings("serial")
public class DelectionAnnotationException extends RuntimeException {

	public DelectionAnnotationException(){
		super();
	}
	
	public DelectionAnnotationException(String msg){
		super(msg);
	}
	
	public DelectionAnnotationException(Throwable t){
		super(t);
	}
	
	public DelectionAnnotationException(String msg,Throwable t){
		super(msg,t);
	}
	
}
