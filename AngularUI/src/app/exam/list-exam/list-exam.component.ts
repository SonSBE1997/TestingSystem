import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';

import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {catchError, finalize} from 'rxjs/operators';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Exam } from 'src/app/entity/Exam.interface';
import { ListExamService } from 'src/app/exam/list-exam/list-exam.service';
import { ListExamDataSource } from 'src/app/exam/list-exam/list-exam.datasource';
import { merge } from 'rxjs/observable/merge';
import {
  debounceTime,
  distinctUntilChanged,
  startWith,
  tap,
  delay,
  map
} from 'rxjs/operators';
import { fromEvent } from 'rxjs/observable/fromEvent';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';


@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit, AfterViewInit {
  listExam: Exam[] = [];

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
  //public dataSource = new MatTableDataSource<Exam>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  //listExam: Exam;

  constructor(
    private listExamService: ListExamService,
    private route: ActivatedRoute,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.findExams( 0, 5, 'userCreated', 'ASC');
  }

  ngAfterViewInit() {
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(tap(() => this.loadExamsPage()))
      .subscribe();
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;

      console.log(this.dataSource.sort.key);
            console.log(this.dataSource.sort.key.id);


  }

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
      }).subscribe(listExam => {
        this.listExam = listExam;
        this.dataSource.data = listExam;
      });
  }

  public loadExamsPage() {
    this.findExams(
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.key,
      this.sort.direction,
    );


  }
  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
