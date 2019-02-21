package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmcglobal.custorm.FilterCustorm;
import com.cmcglobal.entity.Exam;

public interface ExamRepository
    extends JpaRepository<Exam, String>, FilterCustorm {
  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:03 AM
   * Description: TODO - .
   * @param pageable
   * @return
   */
  @Query("Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and "
      + "(e.title like %:searchInput% "
      + "OR u.fullName like %:searchInput% "
      + "OR c.categoryName like %:searchInput% "
      + "OR e.duration like %:searchInput% "
      + "OR e.numberOfQuestion like %:searchInput% "
      + "OR e.status like %:searchInput%) "
      + "GROUP BY e.examId")
  
  List<Exam> pageExam(@Param("searchInput") String searchContent, Pageable pageable);
  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - .
   * @param pageable
   * @return
   */
  @Query("Select e FROM Exam e, User u, Category c "
      + "WHERE e.userCreated = u and e.category = c and "
      + "(e.title like %:searchInput% "
      + "OR u.fullName like %:searchInput% "
      + "OR c.categoryName like %:searchInput% "
      + "OR e.duration like %:searchInput% "
      + "OR e.numberOfQuestion like %:searchInput% "
      + "OR e.status like %:searchInput%) "
      + "GROUP BY e.examId ORDER BY u.fullName asc")
  List<Exam> pageExamSortByUserCreatedByAsc(@Param("searchInput") String searchContent, Pageable pageable);
  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:17 AM
   * Description: TODO - .
   * @param pageable
   * @return
   */
  @Query("Select e FROM Exam e, User u, Category c "
      + "WHERE e.userCreated = u and e.category = c and "
      + "(e.title like %:searchInput% "
      + "OR u.fullName like %:searchInput% "
      + "OR c.categoryName like %:searchInput% "
      + "OR e.duration like %:searchInput% "
      + "OR e.numberOfQuestion like %:searchInput% "
      + "OR e.status like %:searchInput%) "
      + "GROUP BY e.examId "
      + "ORDER BY u.fullName desc")
  List<Exam> pageExamSortByUserCreatedByDesc(@Param("searchInput") String searchContent, Pageable pageable);
  /**
   * Author: ntmduyen.
   * Created date: Feb 21, 2019
   * Created time: 12:13:14 PM
   * Description: TODO - .
   * @param searchContent
   * @param pageable
   * @return
   */
  @Query("Select e FROM Exam e, User u, Category c "
      + "WHERE e.userCreated = u and e.category = c and "
      + "(e.title like %:searchInput% "
      + "OR u.fullName like %:searchInput% "
      + "OR c.categoryName like %:searchInput% "
      + "OR e.duration like %:searchInput% "
      + "OR e.numberOfQuestion like %:searchInput% "
      + "OR e.status like %:searchInput%) "
      + "GROUP BY e.examId "
      + "ORDER BY c.categoryName asc")
  List<Exam> pageExamSortByCategoryAsc(@Param("searchInput") String searchContent, Pageable pageable);
  /**
   * Author: ntmduyen.
   * Created date: Feb 21, 2019
   * Created time: 12:13:19 PM
   * Description: TODO - .
   * @param searchContent
   * @param pageable
   * @return
   */
  @Query("Select e FROM Exam e, User u, Category c "
      + "WHERE e.userCreated = u and e.category = c and "
      + "(e.title like %:searchInput% "
      + "OR u.fullName like %:searchInput% "
      + "OR c.categoryName like %:searchInput% "
      + "OR e.duration like %:searchInput% "
      + "OR e.numberOfQuestion like %:searchInput% "
      + "OR e.status like %:searchInput%) "
      + "GROUP BY e.examId "
      + "ORDER BY c.categoryName desc")
  List<Exam> pageExamSortByCategoryDesc(@Param("searchInput") String searchContent, Pageable pageable);
  
  /**
   * Author: ntmduyen.
   * Created date: Feb 21, 2019
   * Created time: 12:15:11 PM
   * Description: TODO - count exam.
   * @param content
   * @return
   */
  @Query("select count(exam_id) from Exam")
  int countExam();

}
