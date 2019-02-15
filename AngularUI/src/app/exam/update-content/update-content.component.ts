import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { TabInfo } from './update-content.interface';
import { Exam } from 'src/app/entity/Exam.interface';
import { ExamQuestion } from 'src/app/entity/ExamQuestion.interface';
import { ExamService } from 'src/app/service/examService.service';

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
  numberOfRandom = 0;

  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private examService: ExamService
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(pm => {
      this.examId = pm.get('id');
    });

    this.loadData(5);
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
      success => {
        console.log(success);
      },
      error => {
        console.log(error.error.text);
        console.log(error);
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

  changeNumberRandom(e) {
    const value = +e.target.value;
    const maxRandom =
      this.detailExam.numberOfQuestion - this.tabListQuestionInExam.entities;
    if (value > maxRandom) {
      this.numberOfRandom = maxRandom;
    } else if (value < 0) {
      this.numberOfRandom = 0;
    } else {
      this.numberOfRandom = value;
    }
  }

  // click button random
  clickRandom() {
    if (
      this.detailExam.numberOfQuestion > this.detailExam.examQuestions.length
    ) {
      this.http
        .post('http://localhost:8080/exam/random-question', {
          examId: this.detailExam.examId,
          numberOfQuestion: this.numberOfRandom
        })
        .subscribe(
          success => {},
          error => {
            console.log(error.error.text);
            this.loadData(this.tabListQuestionInExam.sizeOfPage);
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

  previousPageTabOne() {
    this.tabListQuestionInExam.currentPage--;
  }

  nextPageTabOne() {
    this.tabListQuestionInExam.currentPage++;
  }

  // reloadData() {
  //   this.http
  //     .get<Exam>(`http://localhost:8080/exam/${this.detailExam.examId}`)
  //     .subscribe(detailExam => {
  //       detailExam.examQuestions = detailExam.examQuestions.sort(function(
  //         a,
  //         b
  //       ) {
  //         return a.id - b.id;
  //       });
  //       this.detailExam = detailExam;
  //       this.backupExamQuestions = detailExam.examQuestions;
  //       // this.examQuestions = detailExam.examQuestions.filter((v, i) => i < 5);
  //       // console.log(this.backupExamQuestions);
  //       this.tabListQuestionInExam = {
  //         currentPage: 0,
  //         sizeOfPage: this.tabListQuestionInExam.sizeOfPage,
  //         entities: detailExam.examQuestions.length
  //       };
  //       // console.log(this.tabListQuestionInExam);
  //     });
  // }

  loadData(sizeOfPage: number) {
    this.examService.getExamById(this.examId).subscribe(detailExam => {
      detailExam.examQuestions = detailExam.examQuestions.sort(function(a, b) {
        return a.id - b.id;
      });
      this.detailExam = detailExam;
      this.backupExamQuestions = detailExam.examQuestions;
      this.tabListQuestionInExam = {
        currentPage: 0,
        sizeOfPage: sizeOfPage,
        entities: detailExam.examQuestions.length
      };
    });
  }

  onTabTwoApply(e) {
    if (e) {
      this.loadData(this.tabListQuestionInExam.sizeOfPage);
      this.inTabOne = true;
    }
  }
}
