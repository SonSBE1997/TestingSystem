import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Question } from 'src/app/entity/Question.interface';
import { TabInfo, Selection } from '../update-content.interface';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ExamService } from 'src/app/service/examService.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-tab-question',
  templateUrl: './tab-question.component.html',
  styleUrls: ['./tab-question.component.css']
})
export class TabQuestionComponent implements OnInit {
  questions: Question[] = [];
  tabAllQuestion: TabInfo;
  selection: Selection[] = [];
  isAdd = false;
  isCheckAll = false;
  examId: string;
  @Input()
  numberOfQuestion: number;
  @Input()
  entities: number;
  @Output()
  apply: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private examService: ExamService
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(pm => {
      this.examId = pm.get('id');
    });

    this.tabAllQuestion = { currentPage: 0, entities: 0, sizeOfPage: 10 };

    this.examService.getQuestionSum().subscribe(sum => {
      this.tabAllQuestion.entities = +sum.headers.get('SumQuestion');
    });

    this.loadDataByPage();
  }

  loadDataByPage() {
    this.examService
      .getQuestions(
        this.tabAllQuestion.currentPage + '',
        this.tabAllQuestion.sizeOfPage + ''
      )
      .subscribe(questions => {
        this.questions = questions;
        this.selection = [];
        questions.forEach(question => {
          // if (this.selection.find(v => v.id === question.questionId)) {
          this.selection.push({ id: question.questionId, checked: false });
          // }
        });
      });
  }

  // change page size tab one
  changePageSizeTabAllQuestion(e) {
    this.tabAllQuestion.sizeOfPage = e.value;
    this.tabAllQuestion.currentPage = 0;
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

  clickSubmitTab2() {
    if (!this.isAdd) {
      return;
    }

    if (this.numberOfQuestion === this.entities) {
      this.apply.emit(false);
      return;
    }

    const selectedQuestion = this.selection.filter(v => v.checked);
    let arr = [];
    selectedQuestion.forEach(v => {
      arr.push({ question: { questionId: v.id } });
    });

    const addNumber = this.numberOfQuestion - this.entities;
    if (arr.length > addNumber) {
      arr = arr.slice(0, addNumber);
    }

    const data = {
      examId: this.examId,
      examQuestions: arr
    };

    this.examService.addListQuestionToExam(data).subscribe(
      success => {},
      error => {
        console.log(error.error.text);
        this.apply.emit(true);
      }
    );

    this.selection.forEach(v => (v.checked = false));
  }

  previousPage() {
    this.tabAllQuestion.currentPage--;
    this.loadDataByPage();
    console.log(this.tabAllQuestion.currentPage);
  }
  nextPage() {
    this.tabAllQuestion.currentPage++;
    this.loadDataByPage();
    console.log(this.tabAllQuestion.currentPage);
  }
}
