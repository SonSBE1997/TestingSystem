package com.cmcglobal.service;

import java.util.List;
import com.cmcglobal.entity.Exam;

public interface ExamService {
	
Exam getOne(String examId);
	
	Exam insert(Exam exam);
	
	Exam update(Exam exam);
	
	List<Exam> readExcel(String exelFilePath);
}
