import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Exam } from 'src/app/entity/Exam.interface';
import { merge } from 'rxjs/observable/merge';
import { mergeMap } from 'rxjs/operators';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ListExamService } from './list-exam.service';
import { v4 as uuid } from 'uuid';

import {
  distinctUntilChanged,
  startWith,
  tap,
  delay,
  map
} from 'rxjs/operators';
import { fromEvent } from 'rxjs/observable/fromEvent';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { MatSortModule } from '@angular/material/sort';
import { Category } from 'src/app/entity/Category.interface';

@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit, AfterViewInit {
  public dataSource = new MatTableDataSource<Exam>();

  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  displayedColumns = [
    'examId',
    'title',
    'category',
    'duration',
    'numberOfQuestion',
    'userCreated',
    'status',
    'createAt'
  ];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  listExam: Exam[] = [];
  listId: string[] = [];
  listDuration: number[] = [];
  numberOfQuestions: number[] = [];
  statuss: String[] = [];
  caterogyNames: String[] = [];
  isCheckALL = false;
  examFrm: FormGroup;

  public duration: number;
  public numberOfQuestion: number;
  public createAt: Date = new Date('dd/mm/yyyy');
  public status: String;
  public category: Category;
  public categoryName: String;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private listExamService: ListExamService
  ) {}
  ngOnInit() {
    this.findExams(0, 5, 'title', 'ASC');
    this.examFrm = this.fb.group({
      duration: [''],
      numberOfQuestion: [''],
      createAt: [''],
      status: [''],
      categoryName: ['']
    });
    this.getDuration();
  }

  ngAfterViewInit() {
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(tap(() => this.loadExamsPage()))
      .subscribe();
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.dataSource.paginator._length = this.paginator._length;

    // console.log(this.paginator._length);
    // console.log(this.dataSource.paginator.getNumberOfPages());
    // console.log(this.dataSource.paginator.page);
  }
  // This function is to find Exams from the API backend
  public findExams = (
    pageNumber = 0,
    pageSize = 5,
    sortTerm = 'title',
    sortOrder = 'ASC'
  ) => {
    this.http
      .get<Exam[]>('http://localhost:8080/exam/listExams/pagination', {
        params: new HttpParams()
          .set('pageNumber', pageNumber.toString())
          .set('pageSize', pageSize.toString())
          .set('sortTerm', sortTerm)
          .set('sortOrder', sortOrder)
      })
      .subscribe(listExam => {
        this.listExam = listExam;
        this.dataSource.data = listExam;
      });
  };

  public loadExamsPage() {
    this.findExams(
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.active,
      this.sort.direction
    );
  }
  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  };
  // Start Delete
  onCheck(event) {
    const input = event.target as HTMLInputElement;
    this.isCheckALL = input.checked;
  }
  onchange(event, examId) {
    let checkId = event.target.checked;
    if (checkId) {
      this.listId.push(examId);
    } else {
      let x = this.listId.findIndex(x => {
        return x === examId;
      });
      if (x !== -1) {
        this.listId.splice(x, 1);
      }
    }
  }
  onCheckAllId(event) {
    let checkId = event.target.checked;
    if (checkId) {
      if (this.listId.length > 0) {
        this.listId = [];
      }
      this.listExam.forEach(x => {
        this.listId.push(x.examId);
      });
    } else {
      this.listId = [];
    }
  }

  deleteAllExam() {
    var r = confirm('Are you sure you want to Permanently delete this exam?');
    if (r == true) {
      if (this.listId.length > 0) {
        this.listId.forEach(element => {
          this.http
            .delete(`http://localhost:8080/exam/${element}`)
            .pipe(
              mergeMap(() =>
                this.http.get<Exam[]>(
                  'http://localhost:8080/exam/listExams/pagination'
                )
              )
            )
            .subscribe(listExam => {
              this.listExam = listExam;
              this.dataSource.data = listExam;
            });
        });
      }
    } else {
    }
  }
  //end

  //Filter Start
  onSubmit() {
    console.log(this.examFrm.value);
    if (this.examFrm.valid) {
      const value = this.examFrm.value;
      const exam: Exam = {
        id: uuid(),
        ...value
      };
      this.http
        .post<Exam[]>('http://localhost:8080/exam/filter', exam)
        .subscribe(listExam => {
          (this.listExam = listExam), console.log(this.listExam);
          this.dataSource.data = listExam;
        });
    }
  }
  getDuration() {
    this.http
      .get('http://localhost:8080/exam/listExams')
      .subscribe((exams: Exam[]) => {
        exams.forEach(x => {
          this.listDuration.push(x.duration);
          this.numberOfQuestions.push(x.numberOfQuestion);
          this.statuss.push(x.status);
          this.caterogyNames.push(x.category.categoryName);
        });
        (this.listDuration = this.listDuration.filter(function(
          item,
          index,
          self
        ) {
          return index === self.indexOf(item);
        })),
          (this.numberOfQuestions = this.numberOfQuestions.filter(function(
            item,
            index,
            self
          ) {
            return index === self.indexOf(item);
          })),
          (this.statuss = this.statuss.filter(function(item, index, self) {
            return index === self.indexOf(item);
          })),
          (this.caterogyNames = this.caterogyNames.filter(function(
            item,
            index,
            self
          ) {
            return index === self.indexOf(item);
          }));
      });
  }
  //end
}
