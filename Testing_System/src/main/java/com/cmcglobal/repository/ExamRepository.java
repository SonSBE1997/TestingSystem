package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cmcglobal.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, String> {
	@Query("SELECT e FROM Exam e")
	List<Exam> pageExam(Pageable pageable);
}
