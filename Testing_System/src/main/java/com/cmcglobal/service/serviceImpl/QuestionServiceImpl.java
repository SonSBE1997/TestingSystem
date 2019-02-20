package com.cmcglobal.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmcglobal.entity.Question;
import com.cmcglobal.repository.QuestionRepository;
import com.cmcglobal.service.QuestionServices;

@Service
public class QuestionServiceImpl implements QuestionServices {

  @Autowired
  QuestionRepository questionRepository;

  @Override
  public List<Question> getAllQuestion() {
    return questionRepository.findAll();
  }

  @Override
  public Question findById(String id) {
    return questionRepository.findById(id).get();
  }

  @Override
  public void insertQuestion(Question question) {
    questionRepository.save(question);
  }

  @Override
  public void deletebyId(String id) {
    questionRepository.deleteById(id);

  }

  @Override
  public ResponseEntity<Question> editQuestion(String id,
      Question newQuestion) {
    Optional<Question> existQ = questionRepository.findById(id);
    if (!existQ.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    newQuestion.setQuestionId(id);
    questionRepository.save(newQuestion);
    return ResponseEntity.noContent().build();
  }

  @Override
  public List<Question> searchByContent(String contentSearch) {
    return questionRepository.findByContentContaining(contentSearch);
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.QuestionServices#updateMultiQuestion(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void updateMultiQuestion(String category_id, String level_id,
      String tag_id, String question_id) {
    // TODO Auto-generated method stub
    questionRepository.updateMultiQuestion(category_id, level_id, tag_id,
        question_id);
  }

  @Override
  public List<Question> pageQuestion(Pageable pageable) {
    return questionRepository.pageQuestion(pageable);
  }

  @Override
  public String countQuestion() {
    return questionRepository.questionSum();
  }

  @Override
  public List<Question> searchByContent(String content, Pageable pageable) {
    return questionRepository.findByContentContaining(content, pageable);
  }

  @Override
  public String countSearchQuestion(String content) {
    content = "%" + content + "%";
    return questionRepository.countSearchQuestion(content);
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.QuestionServices#countQuestionByCategoryId(int)
   * Author: Sanero.
   * Created date: Feb 20, 2019
   * Created time: 1:42:28 PM
   */
  @Override
  public String countQuestionByCategoryId(int categoryId) {
    // TODO Auto-generated method stub
    return questionRepository.questionSumByCategoryId(categoryId);
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.QuestionServices#countSearchQuestionByCategoryId(java.lang.String, int)
   * Author: Sanero.
   * Created date: Feb 20, 2019
   * Created time: 1:42:28 PM
   */
  @Override
  public String countSearchQuestionByCategoryId(String content,
      int categoryId) {
    // TODO Auto-generated method stub
    return questionRepository.countSearchQuestionByCategoryId(content,
        categoryId);
  }

  /* (non-Javadoc)
   * @see com.cmcglobal.service.QuestionServices#findByCategoryIdAndPage(java.lang.String, org.springframework.data.domain.Pageable)
   * Author: Sanero.
   * Created date: Feb 20, 2019
   * Created time: 1:46:58 PM
   */
  @Override
  public List<Question> findByCategoryIdAndPage(int categoryId,
      Pageable pageable) {
    return questionRepository.pageQuestionByCategoryId(categoryId, pageable);
  }
}
