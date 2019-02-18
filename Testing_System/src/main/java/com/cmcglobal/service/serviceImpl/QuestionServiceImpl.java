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

}
