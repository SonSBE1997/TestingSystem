package com.cmcglobal.service;

public class ServiceResult {
	private Status status = Status.SUCCESS;
	private String message;
	private Object data;

	private int totalRecord;

	public enum Status {
		SUCCESS, FAILED;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public ServiceResult() {
		super();
	}

	public ServiceResult(Status status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
