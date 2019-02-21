package com.cmcglobal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "semester_exam")
public class Semester {
	@Id
	@Column(name = "semester_exam_id")
	@GeneratedValue(generator = "auto_increase")
	@GenericGenerator(name = "auto_increase", strategy = "com.cmcglobal.utils.AutoIncreaseId")
	private String id;
	@Column(name = "semester_exam_name")
	private String name;
	@Column(name = "semester_description")
	private String description;
	@Column(name = "status")
	private int status;
	@Column(name = "start_at")
	private Date startTime;
	@Column(name = "end_at")
	private Date endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
}