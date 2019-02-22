/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.service
 * File name: ExamQuestionService.java
 * Author: Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:35:13 PM
 */

package com.cmcglobal.service;

import com.cmcglobal.entity.ExamQuestion;

/*
 * @author Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:35:13 PM
 * Description: TODO - exam-question service. 
 */
public interface ExamQuestionService {
  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:02:44 PM
   * Description: TODO - delete exam question.
   * @param examQuestionId
   */
  public void deleteById(int examQuestionId);

  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:03:15 PM
   * Description: TODO - count all entry exam-question.
   * @return
   */
  public long countAll();
  
  /**
   * Author: Sanero.
   * Created date: Feb 22, 2019
   * Created time: 2:03:40 PM
   * Description: TODO - insert entry to db.
   * @param examQuestion
   */
  public void insert(ExamQuestion examQuestion);
}
