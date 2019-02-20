package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmcglobal.entity.SemesterExam;
import com.cmcglobal.entity.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

	public List<Test> findBySemesterExam(SemesterExam semesterExam);
}