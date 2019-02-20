package com.cmcglobal.service;


public interface UserService {
	ServiceResult getSemseterListByUserId(String id);

	ServiceResult getExamBySemesterExamId(String id);
}
