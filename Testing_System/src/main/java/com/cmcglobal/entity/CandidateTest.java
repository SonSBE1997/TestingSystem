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
@Table(name="candidate_test")
public class CandidateTest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int candidate_test_id ;
	
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidates ;
	
	@ManyToOne
	@JoinColumn(name="test_id")
	private Test tests;
	
	@Column(name="start_time")
	private Date start_time;
	@Column(name="end_time")
	private Date end_time;
	
	@Column(name="correct_number")
	private int correct_number ;
	
	@Column(name="mark")
	private float mark ;
	
	
}
