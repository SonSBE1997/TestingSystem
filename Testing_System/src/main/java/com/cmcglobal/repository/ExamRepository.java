package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmcglobal.entity.Exam;


public interface ExamRepository extends JpaRepository<Exam, String> {

}
