import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Exam, TabInfo } from 'src/app/entity/Exam.interface';

@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit, AfterViewInit {
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
  public dataSource = new MatTableDataSource<Exam>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  listExam: Exam[] = [];
  tabListExam: TabInfo;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getAll();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  public getAll = () => {
    this.http
      .get<Exam[]>('http://localhost:8080/exam/listExams')
      .subscribe(listExam => {
        this.listExam = listExam;
        this.dataSource.data = listExam;
        this.tabListExam = {
          currentPage: 0,
          sizeOfPage: 5,
          entities: listExam.length
        };
      });
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  // change page size
  changePageSize(e) {
    this.tabListExam.sizeOfPage = e.value;
    console.log(this.tabListExam);
  }
}
