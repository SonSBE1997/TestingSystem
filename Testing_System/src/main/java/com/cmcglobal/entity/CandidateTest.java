package com.cmcglobal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "candidate_test")
public class CandidateTest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int candidate_test_id;

  @ManyToOne
  @JoinColumn(name = "candidate_id")
  private Candidate candidates;

  @ManyToOne
  @JoinColumn(name = "test_id")
  private Test tests;

  @Column(name = "start_time")
  private Date start_time;
  @Column(name = "end_time")
  private Date end_time;

  @Column(name = "correct_number")
  private int correct_number;

  @Column(name = "mark")
  private float mark;

  public int getCandidate_test_id() {
    return candidate_test_id;
  }

  public void setCandidate_test_id(int candidate_test_id) {
    this.candidate_test_id = candidate_test_id;
  }

  public Candidate getCandidates() {
    return candidates;
  }

  public void setCandidates(Candidate candidates) {
    this.candidates = candidates;
  }

  public Test getTests() {
    return tests;
  }

  public void setTests(Test tests) {
    this.tests = tests;
  }

  public Date getStart_time() {
    return start_time;
  }

  public void setStart_time(Date start_time) {
    this.start_time = start_time;
  }

  public Date getEnd_time() {
    return end_time;
  }

  public void setEnd_time(Date end_time) {
    this.end_time = end_time;
  }

  public int getCorrect_number() {
    return correct_number;
  }

  public void setCorrect_number(int correct_number) {
    this.correct_number = correct_number;
  }

  public float getMark() {
    return mark;
  }

  public void setMark(float mark) {
    this.mark = mark;
  }

}
