import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Question } from 'src/app/entity/Question.interface';
import { TabInfo, Selection } from '../update-content.interface';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

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
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(pm => {
      this.examId = pm.get('id');
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
      return;
    }
    const selectedQuestion = this.selection.filter(v => v.checked);
    const arr = [];
    selectedQuestion.forEach(v => {
      arr.push({ question: { questionId: v.id } });
    });

    const data = {
      examId: this.examId,
      examQuestions: arr
    };

    this.http.post('http://localhost:8080/exam/add-question', data).subscribe(
      success => {},
      error => {
        console.log(error.error.text);
        this.apply.emit(true);
      }
    );
  }
}
