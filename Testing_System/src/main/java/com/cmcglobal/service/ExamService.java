package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.Exam;

public interface ExamService {
	public List<Exam> fillAll();
	public Exam findByID(String id);
}
