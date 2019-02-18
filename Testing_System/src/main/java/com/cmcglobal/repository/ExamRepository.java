package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cmcglobal.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, String> {
	/**
	 * Author: ntmduyen
	 * Created date: Feb 17, 2019
	 * Created time: 12:51:03 AM
	 * Description: TODO - .
	 * @param pageable
	 * @return
	 */
	@Query("SELECT e FROM Exam e")
	List<Exam> pageExam(Pageable pageable);
	/**
	 * Author: ntmduyen
	 * Created date: Feb 17, 2019
	 * Created time: 12:51:10 AM
	 * Description: TODO - .
	 * @param pageable
	 * @return
	 */
    @Query("SELECT e FROM Exam e,  User u WHERE e.userCreated = u GROUP BY e.examId ORDER BY u.fullName asc")
	List<Exam> pageExamSortByUserCreatedByAsc(Pageable pageable);
    /**
     * Author: ntmduyen
     * Created date: Feb 17, 2019
     * Created time: 12:51:17 AM
     * Description: TODO - .
     * @param pageable
     * @return
     */
    @Query("SELECT e FROM Exam e,  User u WHERE e.userCreated = u GROUP BY e.examId ORDER BY u.fullName desc")
    List<Exam> pageExamSortByUserCreatedByDesc(Pageable pageable);
}
