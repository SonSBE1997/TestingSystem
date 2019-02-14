/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.repository
 * File name: ExamQuestionCategory.java
 * Author: Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:34:04 PM
 */

package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.entity.ExamQuestion;

/*
 * @author Sanero.
 * Created date: Feb 13, 2019
 * Created time: 5:34:04 PM
 * Description: TODO - 
 */
@Repository
public interface ExamQuestionRepository
    extends JpaRepository<ExamQuestion, Integer> {

}
