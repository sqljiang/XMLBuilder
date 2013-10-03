package com.neusoft.xml.builder;

import org.apache.commons.lang3.Validate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.neusoft.xml.builder.Kind.Basic;


import java.util.Collection;

class KindUtil {

	public static boolean isBasic(Class<?> clazz){
		Basic[] basics = Basic.values();
		for(Basic basic : basics){
			if(basic.isSatisfy(clazz)> - 1) return true;
		}
		return false;
	}
	
	public static boolean isBasic(Object o){
		return isBasic(o.getClass());
	}
	
	public static void build(DocumentSupport support,FieldXMLAnnotation fa,Document document,Element parent,Object o,Collection<?> items ){
		for(Object item : items){
			Element e = null;
			if(KindUtil.isBasic(item)){
				String child = fa.child();
				Validate.notNull(child, "child of "+fa.getClass()+" shold not null");
				e = document.createElement(child);
				e.setTextContent(item.toString());
			}else{
				e = support.createElement(document, item);
			}
			parent.appendChild(e);
		}
	}
	
	
}
