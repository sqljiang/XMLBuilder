package com.neusoft.domain;

import java.io.Serializable;
import java.util.Date;

import com.neusoft.xml.builder.ClassXMLAnnotation;
import com.neusoft.xml.builder.FieldXMLAnnotation;
import com.neusoft.xml.builder.Type;

@SuppressWarnings("serial")
@ClassXMLAnnotation(name="log")
public class LogDomain implements Serializable {
	
	@FieldXMLAnnotation(name="id")
	private String id ;
	
	@FieldXMLAnnotation(name="parentId")
	private String parentId = "0";
	
	@FieldXMLAnnotation(name="mId")
	private String mId;
	
	@FieldXMLAnnotation(name="createTime")
	private Date createTime;
	
	@FieldXMLAnnotation(name="expression")
	private String expression;
	
	@FieldXMLAnnotation(name="sourceExpression")
	private String sourceExpression;
	
	@FieldXMLAnnotation(name="operateDocId")
	private String operateDocId;
	
	@FieldXMLAnnotation(name="resultDocId")
	private String resultDocId;
	
	@FieldXMLAnnotation(name="lined")
	private String lined;
	
	@FieldXMLAnnotation(name="marked")
	private String marked;
	
	@FieldXMLAnnotation(name="comments")
	private String comments;
	
	@FieldXMLAnnotation(type=Type.ARRAY,name="childs",child="child")
	private int[] childs ;

	public void setChilds(int[] childs) {
		this.childs = childs;
	}

	public int[] getChilds() {
		return childs;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMId() {
		return mId;
	}

	public void setMId(String id) {
		mId = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getSourceExpression() {
		return sourceExpression;
	}

	public void setSourceExpression(String sourceExpression) {
		this.sourceExpression = sourceExpression;
	}

	public String getOperateDocId() {
		return operateDocId;
	}

	public void setOperateDocId(String operateDocId) {
		this.operateDocId = operateDocId;
	}

	public String getResultDocId() {
		return resultDocId;
	}

	public void setResultDocId(String resultDocId) {
		this.resultDocId = resultDocId;
	}

	public String getLined() {
		return lined;
	}

	public void setLined(String lined) {
		this.lined = lined;
	}

	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof LogDomain)) return false;
		if(obj == this) return true;
		LogDomain log = (LogDomain) obj;
		if(
			id.equals(log.id) && 
			parentId.equals(log.parentId) &&
			mId.equals(log.mId) &&
			createTime.equals(log.createTime) &&
			expression.equals(log.expression) &&
			sourceExpression.equals(log.sourceExpression) &&
			operateDocId.equals(log.operateDocId) &&
			resultDocId.equals(log.resultDocId) &&
			lined.equals(log.lined) &&
			marked.equals(log.marked) &&
			comments.equals(log.comments)
		   ) 
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31*result + (id == null ? 0 : id.hashCode());
		result = 31*result + (parentId == null ? 0 : parentId.hashCode());
		result = 31*result + (mId == null ? 0 : mId.hashCode());
		result = 31*result + (createTime == null ? 0 : createTime.hashCode());
		result = 31*result + (expression == null ? 0 : expression.hashCode());
		result = 31*result + (sourceExpression == null ? 0 : sourceExpression.hashCode());
		result = 31*result + (operateDocId == null ? 0 : operateDocId.hashCode());
		result = 31*result + (resultDocId == null ? 0 : resultDocId.hashCode());
		result = 31*result + (lined == null ? 0 : lined.hashCode());
		result = 31*result + (marked == null ? 0 : marked.hashCode());
		result = 31*result + (comments == null ? 0 : comments.hashCode());
		return result;
	}
	
	
	
}
