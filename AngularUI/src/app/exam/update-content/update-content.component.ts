import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TabInfo } from './update-content.interface';
import { Exam } from 'src/app/entity/Exam.interface';
import { ExamQuestion } from 'src/app/entity/ExamQuestion.interface';
import { ExamService } from 'src/app/service/examService.service';
import { NotifierService } from 'angular-notifier';

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
  numberOption = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private examService: ExamService,
    private notifierService: NotifierService
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
    this.tabListQuestionInExam.entities--;
    event.preventDefault();
    this.detailExam.examQuestions = this.detailExam.examQuestions.filter(
      v => v.id !== id
    );
  }

  clickResetRemoveQuestion() {
    this.detailExam.examQuestions = this.backupExamQuestions;
    this.isRemove = false;
    this.tabListQuestionInExam.entities = this.backupExamQuestions.length;
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

    this.examService.removeListQuestion(exam).subscribe(
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

        if (this.backupExamQuestions.length === 0) {
          this.tabListQuestionInExam.currentPage = 0;
        }

        this.notifierService.notify(
          'success',
          'Remove question successfully',
          ''
        );
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
      const data = {
        examId: this.detailExam.examId,
        numberOfQuestion: this.numberOfRandom
      };
      this.examService.randomQuestion(data).subscribe(
        success => {},
        error => {
          console.log(error.error.text);
          this.loadData(this.tabListQuestionInExam.sizeOfPage);
          this.notifierService.notify(
            'success',
            'Random question successfully',
            ''
          );
        }
      );
    } else {
      this.notifierService.notify('error', 'Can not random question', '');
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

  loadData(sizeOfPage: number) {
    this.examService.getExamById(this.examId).subscribe(detailExam => {
      detailExam.examQuestions = detailExam.examQuestions.sort(function(a, b) {
        return a.id - b.id;
      });
      let maxOption = 0;

      detailExam.examQuestions.forEach(v => {
        maxOption = Math.max(maxOption, v.question.answers.length);
      });

      this.numberOption = Array(maxOption)
        .fill(1)
        .map((v, k) => k);

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
      this.notifierService.notify(
        'success',
        'Add question to exam successfully',
        ''
      );
    } else {
      this.notifierService.notify(
        'error',
        'Can not add question to this exam!',
        ''
      );
    }
  }
}
