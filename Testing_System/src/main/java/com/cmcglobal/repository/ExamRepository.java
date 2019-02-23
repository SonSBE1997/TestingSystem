package com.cmcglobal.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cmcglobal.custorm.FilterCustorm;
import com.cmcglobal.entity.Exam;
import com.cmcglobal.utils.Constants;

/*
 * @author ntmduyen.
 * Created date: Feb 22, 2019
 * Created time: 1:58:30 PM
 * Description: TODO - exam repository.
 */
public interface ExamRepository
    extends JpaRepository<Exam, String>, FilterCustorm {
  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:03 AM
   * Description: TODO - select exam by page.
   * @param pageable
   * @return
   */
  @Query(Constants.Exam.QUERY_PAGE_ALL)
  List<Exam> pageExam(@Param("searchInput") String searchContent,
      Sort pageable);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - select exam sort by created user ascending.
   * @param pageable
   * @return
   */
  @Query(Constants.Exam.QUERY_PAGE_ALL_SORT_BY_USER_CREATED_ASC)
  List<Exam> pageExamSortByUserCreatedByAsc(
      @Param("searchInput") String searchContent);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - select exam sort by created user descending.
   * @param pageable
   * @return
   */
  @Query(Constants.Exam.QUERY_PAGE_ALL_SORT_BY_USER_CREATED_DESC)
  List<Exam> pageExamSortByUserCreatedByDesc(
      @Param("searchInput") String searchContent);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - select list exam sort by category ascending.
   * @param pageable
   * @return
   */
  @Query(Constants.Exam.QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_ASC)
  List<Exam> pageExamSortByCategoryAsc(
      @Param("searchInput") String searchContent);

  /**
   * Author: ntmduyen
   * Created date: Feb 17, 2019
   * Created time: 12:51:10 AM
   * Description: TODO - select list exam sort by category descending.
   * @param pageable
   * @return
   */
  @Query(Constants.Exam.QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_DESC)
  List<Exam> pageExamSortByCategoryDesc(
      @Param("searchInput") String searchContent);
}
