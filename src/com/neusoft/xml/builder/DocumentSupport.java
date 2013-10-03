package com.neusoft.xml.builder;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocumentSupport {
	
	private  static DocumentBuilder builder;
	
	private static Transformer transformer ; 	
	
	private final static Logger LOG = Logger.getLogger(DocumentSupport.class);
	
	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try{
			builder = factory.newDocumentBuilder();
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		}catch (ParserConfigurationException e) {
			LOG.error("document builder create failure", e);
			builder = null;
		}catch (TransformerConfigurationException e) {
			LOG.error("document transformer create failure", e);
			transformer = null;
		}
	}
	
	public <T extends Object> Document create(T o)throws DelectionAnnotationException,IllegalArgumentException{
		Document document = builder.newDocument();
		Element root = createElement(document, o);
		document.appendChild(root);
		return document;
	}
	
	public Document create(){
		return builder.newDocument();
	}
	
	public <T extends Object> Element createElement(Document document, T o) throws NotSpecifyAttributeException,IllegalArgumentException,ClassMatchException{
		Class<?> clazz =  o.getClass();
		Annotation ca = contains(clazz.getAnnotations(), ClassXMLAnnotation.class);
		if(ca == null) {
			throw new DelectionAnnotationException("the class of "+clazz.getName()+" delect "+ ClassXMLAnnotation.class.getName() +" annotation" );
		}
		ClassXMLAnnotation ra = (ClassXMLAnnotation) ca;
		Element parent = document.createElement(ra.name());
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			ca = contains(field.getAnnotations(),FieldXMLAnnotation.class);
			if(ca != null){
				FieldXMLAnnotation fa = (FieldXMLAnnotation) ca;
				field.setAccessible(true);
				Object val = null;
				try{
					val = field.get(o);
					Class<?> type = field.getType();
					Element child = document.createElement(fa.name());
					switch(fa.type()){
						case ATTRBUTE : 
							checkFieldType(Type.ATTRBUTE,type);
							if(val != null){
								parent.setAttribute(fa.name(), val.toString());
							}
							break;
						case ARRAY :
							checkFieldType(Type.ARRAY, type);
							String  childName = fa.child();
							Object obj = Array.get(val, 0);
							if(KindUtil.isBasic(obj)){
								if(childName.equals(""))
									throw new NotSpecifyAttributeException("not specify child attribute"
													+" for annotation of the field: " + field);
								for(int i = 0 , len = Array.getLength(val);i < len ; i++){
									Element e = document.createElement(childName);
									e.setTextContent(Array.get(val, i).toString());
									child.appendChild(e);
								}
							}else{
								for(int i = 0,len = Array.getLength(val); i < len ; i++){
									child.appendChild(createElement(document, Array.get(val, i)));
								}
							}
							parent.appendChild(child);
							break;
						case COLLECTION :
							checkFieldType(Type.COLLECTION,type);
							CollectionResponsibility responsibility = new CollectionResponsibility();
							responsibility.chain(this, fa, document, child, val);
							parent.appendChild(child);
							break;
						case ELEMENT :
							checkFieldType(Type.ELEMENT,type);
							if(val != null){
								child.setTextContent(val.toString());
							}
							parent.appendChild(child);
							break;
						case REFERENCE :
							checkFieldType(Type.REFERENCE,type);
							if(val != null){
								child.appendChild(createElement(document, val));
							}
							parent.appendChild(child);
							break;
						default :
							throw new IllegalArgumentException("the XMLElementAnnotation not have " +fa.type().toString() +" type");
					}
				}catch (IllegalAccessException e) {
					LOG.error("the field of "+ field.getName() +" cann't be accessed", e);
				}
			}
		}
		return parent;
	}
	
	private <T extends Annotation> Annotation contains(Annotation[] annotations,Class<T> clazz){
		for(Annotation annotation : annotations){
			if(clazz.isAssignableFrom(annotation.getClass())){
				return annotation;
			}
		}
		return null;
	}
	
	private int checkFieldType(Type type,Class<?> clazz)throws ClassMatchException{
		int result = type.isSatisfy(clazz);
		if(result < 0){
			throw new ClassMatchException("the file type is not match");
		}
		return result;
	}
	
	/**
	 * Document transform to a XML file
	 * @param document
	 * @return
	 */
	public String transform(Document document){
		Validate.notNull(document);
		Source source = new DOMSource(document);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(output);
		try{
			transformer.transform(source, result);
		}catch (TransformerException e) {
			LOG.error("document transform to string failure", e);
		}
		return output.toString();
	}
	
}
