package com.cmcglobal.service;

import com.cmcglobal.entity.SemesterExam;

public interface SemesterExamService {
	public abstract ServiceResult getAllSemesterExam();

	public abstract ServiceResult search(String keyword);

	public ServiceResult create(SemesterExam keyword);

	public ServiceResult delete(String id);

	public ServiceResult findById(String id);
	
	public ServiceResult filter(String name);

}
