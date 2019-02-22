package com.cmcglobal.entity;

public class TestDetail {
	private Test test;
	private int isTest;
	
	public TestDetail(Test test, int isTest) {
		this.test = test;
		this.isTest = isTest;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public int getIsTest() {
		return isTest;
	}

	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}

}