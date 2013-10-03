package com.neusoft.xml.builder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.neusoft.xml.builder.Kind.Collection;


class CollectionResponsibility {

	public void chain(DocumentSupport support,FieldXMLAnnotation fa,Document document,Element parent,Object o){
		for(Collection collection : Collection.values()){
			if(collection.handler(support, fa, document, parent, o)) return;
		}
	}
	
}
