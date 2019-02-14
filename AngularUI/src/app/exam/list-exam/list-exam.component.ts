import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Exam } from 'src/app/entity/Exam.interface';

@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit, AfterViewInit {
  displayedColumns = ['examId', 'title', 'category', 'duration', 'numberOfQuestion', 'userCreated', 'status', 'createAt'];
  public dataSource = new MatTableDataSource<Exam>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  listExam: Exam[] = [];
  constructor(private http: HttpClient) { }
  ngOnInit() {
    this.getAll();
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  public getAll = () => {
    this.http.get<Exam[]>('http://localhost:80/exam/listExams')
    .subscribe(listExam => {
      this.listExam = listExam;
      this.dataSource.data = listExam;
    });
  }
  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
