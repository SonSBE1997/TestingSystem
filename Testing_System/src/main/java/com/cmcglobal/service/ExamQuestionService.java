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
 * Description: TODO - 
 */
public interface ExamQuestionService {
  public void deleteById(int examQuestionId);

  public long countAll();
  
  public void insert(ExamQuestion examQuestion);
}
