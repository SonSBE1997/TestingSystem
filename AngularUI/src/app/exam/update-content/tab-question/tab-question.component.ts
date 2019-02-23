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
  @Input()
  categoryId: number;
  @Output()
  apply: EventEmitter<boolean> = new EventEmitter();
  isSort = 0;
  backupSort: Question[] = [];
  searchStr = '';
  isSearching = false;
  numberOption = [];
  optionWidth = '';
  maxOption = 0;
  reset = false;

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

  // reset all info to default.
  resetInfo() {
    this.reset = false;
    this.selection = [];
    this.isCheckAll = false;
    this.tabAllQuestion.currentPage = 0;
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

    if (this.reset) {
      this.resetInfo();
    }

    observable.subscribe(questions => {
      this.questions = questions;
      // this.selection = [];

      questions.forEach(question => {
        const existedQuestion = this.selection.filter(
          v => v.id === question.questionId
        );
        if (existedQuestion.length === 0) {
          let select: Selection = {
            id: question.questionId,
            checked: false,
            status: false
          };

          if (question.category.categoryId === this.categoryId) {
            select.status = true;
            if (this.isCheckAll) {
              select.checked = this.isCheckAll;
            }
          } else {
            select.status = false;
          }

          this.selection.push(select);
          this.maxOption = Math.max(this.maxOption, question.answers.length);
        }
      });
      this.numberOption = [];
      this.numberOption = Array(this.maxOption)
        .fill(1)
        .map((v, k) => k);
      this.optionWidth = 74 / this.maxOption + '%';
    });
  }

  // change page size tab one
  changePageSizeTabAllQuestion(e) {
    this.tabAllQuestion.sizeOfPage = e.value;
    this.tabAllQuestion.currentPage = 0;
    // this.isCheckAll = false;
    this.loadDataByPage();
  }

  // click checkbox question
  selectQuestion(questionId) {
    console.log(questionId);

    let count = 0;
    this.selection.forEach(item => {
      if (item.status === true) {
        if (item.id === questionId) {
          item.checked = !item.checked;
        }

        if (item.checked) {
          count++;
        }
      } else {
        item.checked = false;
      }
    });

    if (count > 0) {
      this.isAdd = true;
    } else if (count === 0) {
      this.isAdd = false;
    }

    const selectAvailable = this.selection.filter(v => v.status === true);

    if (count === selectAvailable.length && count > 0) {
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
      if (item.status === true) {
        item.checked = this.isCheckAll;
      }
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
        this.isCheckAll = false;
        this.isAdd = false;
      }
    );

    this.selection.forEach(v => (v.checked = false));
  }

  previousPage() {
    this.tabAllQuestion.currentPage--;
    this.loadDataByPage();
    // this.isCheckAll = false;
    // console.log(this.tabAllQuestion.currentPage);
  }
  nextPage() {
    this.tabAllQuestion.currentPage++;
    this.loadDataByPage();
    // this.isCheckAll = false;
    // console.log(this.tabAllQuestion.currentPage);
  }

  sortTableByContent() {
    // console.log(this.isSort);
    // console.log(this.questions);
    // console.log(this.backupSort);
    if (this.isSort === 0) {
      this.isSort = 1;
      this.backupSort = this.questions.map(v => v);
      this.questions = this.questions.sort(function(a, b) {
        return a.content > b.content ? 1 : 0;
      });
      return;
    }

    if (this.isSort === 1) {
      this.questions = this.questions.reverse();
      this.isSort = 2;
      // console.log(this.backupSort);
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
    this.reset = true;
    this.loadDataByPage();
  }

  keyPressSearch(e) {
    if (e.charCode === 13) {
      this.clickSearch();
    }
  }
}
