package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmcglobal.custorm.FilterCustorm;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.utils.Contants;
public interface ExamRepository
    extends JpaRepository<Exam, String>, FilterCustorm {
  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:03 AM
   * Description: TODO - show the page of exam.
   * @param pageable
   * @return
   */
  @Query(Contants.Exam.QUERY_PAGE_ALL)
  
  List<Exam> pageExam(@Param("searchInput") String searchContent, Sort pageable);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - show the page of exam.
   * @param pageable
   * @return
   */
  @Query(Contants.Exam.QUERY_PAGE_ALL_SORT_BY_USER_CREATED_ASC)
  List<Exam> pageExamSortByUserCreatedByAsc(@Param("searchInput") String searchContent);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:17 AM
   * Description: TODO - show the page of exam.
   * @param pageable
   * @return
   */
  @Query(Contants.Exam.QUERY_PAGE_ALL_SORT_BY_USER_CREATED_DESC)
  List<Exam> pageExamSortByUserCreatedByDesc(@Param("searchInput") String searchContent);
  
  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:53:51 PM
   * Description: TODO - show the page of exam.
   * @param searchContent
   * @return
   */
  @Query(Contants.Exam.QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_ASC)
  List<Exam> pageExamSortByCategoryAsc(@Param("searchInput") String searchContent);
  
  /**
   * Author: ntmduyen.
   * Created date: Feb 22, 2019
   * Created time: 1:53:58 PM
   * Description: TODO - .
   * @param searchContenshow the page of examt
   * @return
   */
  @Query(Contants.Exam.QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_DESC)
  List<Exam> pageExamSortByCategoryDesc(@Param("searchInput") String searchContent);
}
