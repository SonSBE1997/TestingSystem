/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.utils
 * File name: Helper.java
 * Author: Sanero.
 * Created date: Feb 14, 2019
 * Created time: 9:00:13 AM
 */

package com.cmcglobal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cmcglobal.entity.ExamQuestion;
import com.cmcglobal.entity.Question;

/*
 * @author Sanero.
 * Created date: Feb 14, 2019
 * Created time: 9:00:13 AM
 * Description: TODO - 
 */
public class Helper {
  /**
   * Author: Sanero.
   * Created date: Feb 14, 2019
   * Created time: 9:02:29 AM
   * Description: TODO - random order choice of question.
   * @param random
   * @return
   */
  public static String randomChoiceOrder(Random random, int numberRandom) {
    ArrayList<Integer> arr = new ArrayList<>();
    for (int i = 0; i < numberRandom; i++) {
      arr.add(i + 1);
    }
    String choiceOrder = "";
    while (numberRandom > 0) {
      int index = random.nextInt(numberRandom);
      int number = arr.get(index);
      choiceOrder += number + " ";
      arr.remove(index);
      numberRandom--;
    }
    return choiceOrder.trim();
  }

  /**
   * Author: Sanero.
   * Created date: Feb 14, 2019
   * Created time: 9:49:58 AM
   * Description: TODO - .
   * @param random
   * @param questions
   * @param numberRandom
   * @return
   */
  public static List<ExamQuestion> randomQuestion(Random random,
      List<Question> questions, int numberRandom, String examId) {
    int countQuestion = questions.size();
    List<ExamQuestion> examQuestions = new ArrayList<>();
    while (numberRandom > 0) {
      numberRandom--;
      int index = random.nextInt(countQuestion);
      ExamQuestion examQuestion = new ExamQuestion();
      examQuestion.setExamId(examId);
      Question question = questions.get(index);
      examQuestion.setQuestion(question);
      String choiceOrder = randomChoiceOrder(random,
          question.getAnswers().size());
      examQuestion.setChoiceOrder(choiceOrder);
      examQuestions.add(examQuestion);
    }
    return examQuestions;
  }
}
