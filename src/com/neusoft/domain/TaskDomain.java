package com.neusoft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang.Validate;

import com.neusoft.quartz.xml.FieldXMLAnnotation;
import com.neusoft.quartz.xml.Type;
import com.neusoft.quartz.xml.ClassXMLAnnotation;

@SuppressWarnings("serial")
@ClassXMLAnnotation(name="task")
public class TaskDomain implements Serializable {
	
	@FieldXMLAnnotation(name="taskId")
	private String taskId;
	
	@FieldXMLAnnotation(name="userId")
	private String userId;
	
	@FieldXMLAnnotation(name="checkDocId")
	private String checkDocId;
	
	@FieldXMLAnnotation(name="taskCreateTime")
	private Date taskCreateTime ;
	
	@FieldXMLAnnotation(type=Type.COLLECTION,name="items")
	private HashSet<LogDomain> logs = new HashSet<LogDomain>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCheckDocId() {
		return checkDocId;
	}

	public void setCheckDocId(String checkDocId) {
		this.checkDocId = checkDocId;
	}

	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public HashSet<LogDomain> getLogs() {
		return logs;
	}
	
	public void addLog(LogDomain log){
		Validate.notNull(log);
		logs.add(log);
	}
	
}
