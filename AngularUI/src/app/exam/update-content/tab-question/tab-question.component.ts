import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Question } from 'src/app/entity/Question.interface';
import { TabInfo, Selection } from '../update-content.interface';
import { ActivatedRoute } from '@angular/router';
import { ExamService } from 'src/app/service/examService.service';
import { NotifierService } from 'angular-notifier';

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
  isSort = 0;
  backupSort: Question[] = [];
  searchStr = '';
  isSearching = false;
  numberOption = [];
  optionWidth = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private examService: ExamService,
    private notifierService: NotifierService
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(pm => {
      this.examId = pm.get('id');
    });

    this.tabAllQuestion = { currentPage: 0, entities: 0, sizeOfPage: 10 };

    this.loadDataByPage();
  }

  loadDataByPage() {
    let observable;
    if (this.isSearching) {
      observable = this.examService.searchQuestionByContent(
        this.searchStr,
        this.tabAllQuestion.currentPage + '',
        this.tabAllQuestion.sizeOfPage + ''
      );
      // console.log(this.searchStr);

      this.examService.countSearchQuestion(this.searchStr).subscribe(h => {
        this.tabAllQuestion.entities = +h.headers.get('CountSearchQuestion');
      });
    } else {
      observable = this.examService.getQuestions(
        this.tabAllQuestion.currentPage + '',
        this.tabAllQuestion.sizeOfPage + ''
      );

      this.examService.getQuestionSum().subscribe(sum => {
        this.tabAllQuestion.entities = +sum.headers.get('SumQuestion');
      });
    }

    observable.subscribe(questions => {
      this.questions = questions;
      this.selection = [];

      let maxOption = 0;
      questions.forEach(question => {
        maxOption = Math.max(maxOption, question.answers.length);
        this.selection.push({ id: question.questionId, checked: false });
      });
      this.numberOption = Array(maxOption)
        .fill(1)
        .map((v, k) => k);
      this.optionWidth = 74 / maxOption + '%';
    });
  }

  // change page size tab one
  changePageSizeTabAllQuestion(e) {
    this.tabAllQuestion.sizeOfPage = e.value;
    this.tabAllQuestion.currentPage = 0;
    this.loadDataByPage();
  }

  // click checkbox question
  selectQuestion(questionId) {
    // console.log(questionId);
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
      this.notifierService.notify('warning', 'Enough question already!');
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
    this.isCheckAll = false;
    // console.log(this.tabAllQuestion.currentPage);
  }
  nextPage() {
    this.tabAllQuestion.currentPage++;
    this.loadDataByPage();
    this.isCheckAll = false;
    // console.log(this.tabAllQuestion.currentPage);
  }

  sortTableByContent() {
    if (this.isSort === 0) {
      this.backupSort = this.questions;
      this.questions = this.questions.sort(function(a, b) {
        return a.questionId > b.questionId ? 1 : 0;
      });
      this.isSort = 1;
      return;
    }

    if (this.isSort === 1) {
      this.questions = this.questions.reverse();
      this.isSort = 2;
      return;
    }

    if (this.isSort === 2) {
      this.isSort = 0;
      this.questions = this.backupSort;
    }
  }

  clickSearch() {
    this.tabAllQuestion.currentPage = 0;
    if (this.searchStr !== '') {
      this.isSearching = true;
    } else {
      this.isSearching = false;
    }
    this.loadDataByPage();
  }

  keyPressSearch(e) {
    if (e.charCode === 13) {
      this.clickSearch();
    }
  }
}
