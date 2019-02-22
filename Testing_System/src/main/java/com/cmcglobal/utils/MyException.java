package com.cmcglobal.utils;

public class MyException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String messException;
	public MyException(String messException) {
		super();
		this.messException = messException;
	}
	public String getMessException() {
		return messException;
	}
	public void setMessException(String messException) {
		this.messException = messException;
	}
}
