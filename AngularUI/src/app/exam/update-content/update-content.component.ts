import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { TabInfo } from './update-content.interface';
import { Exam } from 'src/app/entity/Exam.interface';
import { ExamQuestion } from 'src/app/entity/ExamQuestion.interface';

@Component({
  selector: 'app-update-content',
  templateUrl: './update-content.component.html',
  styleUrls: ['./update-content.component.css']
})
export class UpdateContentComponent implements OnInit {
  detailExam: Exam;
  backupExamQuestions: ExamQuestion[] = [];

  tabListQuestionInExam: TabInfo;

  inTabOne = true;
  isRemove = false;
  examId: string;

  constructor(
    private activatedRoute: ActivatedRoute,
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
        // this.examQuestions = detailExam.examQuestions.filter((v, i) => i < 5);
        // console.log(this.backupExamQuestions);
        this.tabListQuestionInExam = {
          currentPage: 0,
          sizeOfPage: 5,
          entities: detailExam.examQuestions.length
        };
        // console.log(this.tabListQuestionInExam);
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



  // click button submit
  clickUpdate() {
    this.isRemove = false;
    const data = this.backupExamQuestions.filter(
      v => !this.detailExam.examQuestions.includes(v)
    );
    const exam = {
      examId: this.detailExam.examId,
      examQuestions: data
    };
    console.log(exam);

    this.http.put('http://localhost:8080/exam/remove-question', exam).subscribe(
      success => {},
      error => {
        console.log(error.error.text);
        this.backupExamQuestions = this.backupExamQuestions.filter(
          v => !data.includes(v)
        );
        const entities = this.backupExamQuestions.length;
        this.tabListQuestionInExam.entities = entities;
        if (
          this.tabListQuestionInExam.currentPage *
            this.tabListQuestionInExam.sizeOfPage ===
          entities
        ) {
          this.tabListQuestionInExam.currentPage--;
        }
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
    this.tabListQuestionInExam.currentPage = 0;
  }

  priviousPageTabOne() {
    this.tabListQuestionInExam.currentPage--;
  }

  nextPageTabOne() {
    this.tabListQuestionInExam.currentPage++;
  }
}
