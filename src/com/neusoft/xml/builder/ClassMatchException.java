package com.neusoft.xml.builder;

@SuppressWarnings("serial")
public class ClassMatchException extends RuntimeException {

	public ClassMatchException(){
		super();
	}
	
	public ClassMatchException(String msg){
		super(msg);
	}
	
	public ClassMatchException(Throwable t){
		super(t);
	}
	
	public ClassMatchException(String msg,Throwable t){
		super(msg,t);
	}
	
}
