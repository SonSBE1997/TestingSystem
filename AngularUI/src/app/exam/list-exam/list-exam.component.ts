import { HttpResponse, HttpEventType, HttpClient } from '@angular/common/http';
import { UploadserviceService } from 'src/app/service/upload/uploadservice.service';
import { NotifierService } from 'angular-notifier';
import { error } from 'util';

import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpParams } from '@angular/common/http';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Exam } from 'src/app/entity/Exam.interface';
import { merge } from 'rxjs/observable/merge';
import {distinctUntilChanged, startWith, tap, delay, map } from 'rxjs/operators';
import { fromEvent } from 'rxjs/observable/fromEvent';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { MatSortModule } from '@angular/material/sort';


@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})

export class ListExamComponent implements OnInit, AfterViewInit {
  selectedFiles: FileList;
  currentFileUpload: File;
  currentFileInport: File;
  mess: string;
  check: boolean;

  constructor(private uploadService: UploadserviceService, private http: HttpClient, private notifierService: NotifierService) { }

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

  ngOnInit() {
    this.findExams(0, 5, 'title', 'ASC');
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
        this.notifierService.notify('error', 'Upload failed!')
        console.log('upload is failed!')
        console.log("sfsff: " + event.type)
      }

      this.notifierService.notify('success', 'File is completely uploaded!');
      console.log('File is completely uploaded!')

      this.uploadService.importToServer(this.currentFileUpload)
        .subscribe(
          //   event => {
          //   if (event instanceof HttpResponse) {
          //     this.notifierService.notify('success', 'Import exam successfully');
          //   } else {
          //     this.notifierService.notify('error', 'Import failed!');
          //   }
          // }
           success => {
           },
           error => {
               console.log("error: " + error.error.text);
              if (error.error.text === 'Ok') {
                this.notifierService.notify('success', 'Import exam successfully');
              }else if(error.error.text  === 'not Ok'){
                this.notifierService.notify('error', 'Import exam Failed');
              }
            }
        );

    });
    this.selectedFiles = undefined;

    // success => {
    // this.notifierService.notify('success', 'Import exam successfully');
    // },
    // error => {
    //     console.log(error.error.text);
    //    if (error.error.text !== 'Ok') {
    //      this.notifierService.notify('error', 'Import exam Failed');
    //    }
    //  }
    this.findExams( 0, 5, 'title', 'ASC');
  }

  ngAfterViewInit() {
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(tap(() => this.loadExamsPage()))
      .subscribe();
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.paginator._length=this.paginator._length;

      console.log(this.paginator._length);
      console.log(this.dataSource.paginator.getNumberOfPages());
      console.log(this.dataSource.paginator.page);
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
      }).subscribe(listExam => {
        this.listExam = listExam;
        this.dataSource.data = listExam;
      });
  }

  public loadExamsPage() {
    this.findExams(
      this.paginator.pageIndex,
      this.paginator.pageSize,
      this.sort.active,
      this.sort.direction,
    );
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
