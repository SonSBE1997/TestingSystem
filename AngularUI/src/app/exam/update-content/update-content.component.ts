import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Event } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import {
  Exam,
  Question,
  Selection,
  TabInfo,
  ExamQuestion
} from './update-content.interface';

@Component({
  selector: 'app-update-content',
  templateUrl: './update-content.component.html',
  styleUrls: ['./update-content.component.css']
})
export class UpdateContentComponent implements OnInit {
  detailExam: Exam;
  backupExamQuestions: ExamQuestion[] = [];
  questions: Question[] = [];
  selection: Selection[] = [];
  isCheckAll = false;
  tabListQuestionInExam: TabInfo;
  tabAllQuestion: TabInfo;
  inTabOne = true;
  isRemove = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.http.get<Exam>(`http://localhost:8080/exam/${id}`);
        })
      )
      .subscribe(detailExam => {
        detailExam.examQuestions = detailExam.examQuestions.sort(function(
          a,
          b
        ) {
          return a.id - b.id;
        });
        this.detailExam = detailExam;
        this.backupExamQuestions = detailExam.examQuestions;
        // console.log(this.backupExamQuestions);
        this.tabListQuestionInExam = {
          currentPage: 0,
          sizeOfPage: 5,
          entities: detailExam.examQuestions.length
        };
        // console.log(this.tabListQuestionInExam);
      });

    this.http
      .get<Question[]>(`http://localhost:8080/question/all`)
      .subscribe(questions => {
        this.questions = questions;
        questions.forEach(question => {
          this.selection.push({ id: question.questionId, checked: false });
        });
      });
  }

  // click remove question
  removeQuestion(event, id) {
    this.isRemove = true;
    event.preventDefault();
    // console.log(id);
    this.detailExam.examQuestions = this.detailExam.examQuestions.filter(
      v => v.id !== id
    );
    // console.log(this.detailExam.examQuestions);
  }

  clickResetRemoveQuestion() {
    this.detailExam.examQuestions = this.backupExamQuestions;
    this.isRemove = false;
  }

  // click checkbox question
  selectQuestion(questionId) {
    console.log(questionId);
    this.selection.forEach(item => {
      if (item.id === questionId) {
        item.checked = !item.checked;
      }
    });

    const isAll = this.selection.filter(v => v.checked);
    if (isAll.length === this.selection.length) {
      this.isCheckAll = true;
    } else {
      this.isCheckAll = false;
    }
    // console.log(JSON.stringify(this.selection));
  }

  // click checkbox all
  selectAll() {
    this.isCheckAll = !this.isCheckAll;
    this.selection.forEach(item => {
      item.checked = this.isCheckAll;
    });
  }

  // click button submit
  clickUpdate() {
    this.isRemove = false;
    this.http
      .put('http://localhost:8080/exam/remove-question', this.detailExam)
      .subscribe(
        success => {},
        error => {
          console.log(error.error.text);
        }
      );
  }

  // click button random
  clickRandom(event) {
    console.log('random');
  }

  // change to tab one
  changeTabOne() {
    this.inTabOne = true;
  }

  // change to tab two
  changeTabTwo() {
    this.inTabOne = false;
  }

  // change page size tab one
  changePageSizeTabOne(e) {
    this.tabListQuestionInExam.sizeOfPage = e.value;
    console.log(this.tabListQuestionInExam);
  }
}
