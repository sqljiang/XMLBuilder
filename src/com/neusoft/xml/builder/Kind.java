package com.neusoft.xml.builder;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Document;

interface Kind {
	
	enum Basic implements Kind{
		SHORT(Short.class),
		INTEGER(Integer.class),
		LONG(Long.class),
		FLOAT(Float.class),
		DOUBLE(Double.class),
		BOOLEAN(Boolean.class),
		ENUM(Enum.class),
		DATE(Date.class),
		STRING(String.class);
		
		private Class<?> clazz ;
		
		private Basic(Class<?> clazz){
			this.clazz = clazz;
		}
		
		public int isSatisfy(Class<?> clazz){
			if(clazz.isAssignableFrom(this.clazz)) return this.ordinal();
			return -1;
		}
		
	}
	
	enum Reference implements Kind{
		BEAN(Object.class);
		
		private Class<? extends Object> clazz;
		
		private Reference(Class<? extends Object> clazz){
			this.clazz = clazz;
		}
		
		public int isSatisfy(Class<?> clazz){
			if(this.clazz.isAssignableFrom(clazz)) return this.ordinal();
			return -1;
		}
		
	}
	
	enum Array implements Kind{
		Array;
		
		public int isSatisfy(Class<?> clazz){
			if(clazz.isArray()) return this.ordinal();
			return -1;
		}
		
	}
	
	enum Collection implements Kind{
		LIST(List.class){
			public boolean handler(DocumentSupport support,FieldXMLAnnotation fa,Document document,Element parent,Object o){
				if(this.isSatisfy(o.getClass()) > -1){
					List<?> items = List.class.cast(o);
					KindUtil.build(support, fa, document, parent, o, items);
					return true;
				}
				return false;
			}
		},
		SET(Set.class){
			public boolean handler(DocumentSupport support,FieldXMLAnnotation fa,Document document,Element parent,Object o){
				if(this.isSatisfy(o.getClass()) > -1){
					Set<?> items = Set.class.cast(o);
					KindUtil.build(support, fa, document, parent, o, items);
					return true;
				}
				return false;
			}
		},
		MAP(Map.class){
			public boolean handler(DocumentSupport support,FieldXMLAnnotation fa,Document document,Element parent, Object o){
				if(this.isSatisfy(o.getClass()) > -1){
					
					return true;
				}
				return false;
			}
		};
		
		private Class<?> clazz;
		
		private Collection(Class<?> clazz){
			this.clazz = clazz;
		}
		
		public int isSatisfy(Class<?> clazz){
			if(this.clazz.isAssignableFrom(clazz)) return this.ordinal();
			return -1;
		}
		
		abstract boolean  handler(DocumentSupport support,FieldXMLAnnotation fa ,Document document,Element parent,Object o);
		
	}
	
	public int isSatisfy(Class<?> clazz);
	
}
