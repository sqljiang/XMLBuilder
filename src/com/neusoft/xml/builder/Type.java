package com.neusoft.xml.builder;

public enum Type{
	ARRAY(Kind.Array.class),
	ATTRBUTE(Kind.Basic.class),
	COLLECTION(Kind.Collection.class),
	ELEMENT(Kind.Basic.class),
	REFERENCE(Kind.Reference.class);
	
	private Kind[] kinds; 
	
	private Type(Class<? extends Kind> kind){
		this.kinds = kind.getEnumConstants();
	}
	
	public int isSatisfy(Class<?> clazz){
		int result = -1;
		for(Kind kind : kinds){
			result = kind.isSatisfy(clazz);
			if(result > -1) return result;
		}
		return result;
	}
	
}
