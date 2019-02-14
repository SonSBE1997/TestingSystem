/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.service.serviceImpl
 * File name: ExamQuestionServiceImpl.java
 * Author: Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:36:56 PM
 */

package com.cmcglobal.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.ExamQuestion;
import com.cmcglobal.repository.ExamQuestionRepository;
import com.cmcglobal.service.ExamQuestionService;

/*
 * @author Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:36:56 PM
 * Description: TODO - 
 */
@Service
@Transactional
public class ExamQuestionServiceImpl implements ExamQuestionService {

  @Autowired
  ExamQuestionRepository repository;

  /* (non-Javadoc)
  
  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamQuestionService#countAll()
   * Author: Sanero.
   * Created date: Feb 13, 2019
   * Created time: 5:42:03 PM
   */
  @Override
  public long countAll() {
    return repository.count();
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamQuestionService#delete(int)
   * Author: Sanero.
   * Created date: Feb 13, 2019
   * Created time: 5:55:04 PM
   */
  @Override
  public void delete(int examQuestionId) {
    repository.deleteById(examQuestionId);
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.ExamQuestionService#insert(com.cmcglobal.entity.ExamQuestion)
   * Author: Sanero.
   * Created date: Feb 14, 2019
   * Created time: 8:33:24 AM
   */
  @Override
  public void insert(ExamQuestion examQuestion) {
    repository.save(examQuestion);
  }
}
