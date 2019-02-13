import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Event } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { Exam, Question, Selection, TabInfo } from './update-content.interface';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-update-content',
  templateUrl: './update-content.component.html',
  styleUrls: ['./update-content.component.css']
})
export class UpdateContentComponent implements OnInit {
  detailExam: Exam;
  questions: Question[] = [];
  selection: Selection[] = [];
  isCheckAll = false;
  tabListQuestionInExam: TabInfo;
  tabAllQuestion: TabInfo;
  inTabOne = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.http.get<Exam>(`http://localhost:3000/exam/${id}`);
        })
      )
      .subscribe(detailExam => {
        this.detailExam = detailExam;
        this.tabListQuestionInExam = {
          currentPage: 0,
          sizeOfPage: 5,
          entities: detailExam.examQuestions.length
        };
        // console.log(this.tabListQuestionInExam);
      });

    this.http
      .get<Question[]>(`http://localhost:3000/questions`)
      .subscribe(questions => {
        this.questions = questions;
        questions.forEach(question => {
          this.selection.push({ id: question.id, checked: false });
        });
      });
  }

  // click remove question
  removeQuestion(event, questionId) {
    event.preventDefault();
    console.log(questionId);
  }

  // click checkbox question
  selectQuestion(questionId) {
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
    console.log('update');
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
