import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
  isAdd = false;
  examId: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          this.examId = id;
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
    let count = 0;
    this.selection.forEach(item => {
      if (item.id === questionId) {
        item.checked = !item.checked;
      }

      if (item.checked) {
        count++;
      }
    });

    if (count > 0) {
      this.isAdd = true;
    } else if (count === 0) {
      this.isAdd = false;
    }

    if (count === this.selection.length) {
      this.isCheckAll = true;
    } else {
      this.isCheckAll = false;
    }
    // console.log(JSON.stringify(this.selection));
  }

  // click checkbox all
  selectAll() {
    this.isCheckAll = !this.isCheckAll;
    this.isAdd = this.isCheckAll;
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

  clickSubmitTab2() {
    if (!this.isAdd) {
      return;
    }
    const selectedQuestion = this.selection.filter(v => v.checked);
    const arr = [];
    selectedQuestion.forEach(v => {
      arr.push({ question: { questionId: v.id } });
    });

    const data = {
      examId: this.detailExam.examId,
      examQuestions: arr
    };

    this.http.post('http://localhost:8080/exam/add-question', data).subscribe(
      success => {},
      error => {
        console.log(error.error.text);
        window.location.reload();
      }
    );
  }

  // click button random
  clickRandom() {
    if (
      this.detailExam.numberOfQuestion > this.detailExam.examQuestions.length
    ) {
      this.http
        .post('http://localhost:8080/exam/random-question', {
          examId: this.detailExam.examId
        })
        .subscribe(
          success => {},
          error => {
            console.log(error.error.text);
            window.location.reload();
          }
        );
    }
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
