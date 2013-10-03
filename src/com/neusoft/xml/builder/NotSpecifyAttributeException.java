package com.neusoft.xml.builder;

@SuppressWarnings("serial")
public class NotSpecifyAttributeException extends DelectionAnnotationException {

	public NotSpecifyAttributeException(){
		super();
	}
	
	public NotSpecifyAttributeException(String msg){
		super(msg);
	}
	
	public NotSpecifyAttributeException(Throwable t){
		super(t);
	}
	
	public NotSpecifyAttributeException(String msg,Throwable t){
		super(msg,t);
	}
	
}
