import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListExams } from './listExam.interface';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit, AfterViewInit {
  displayedColumns = ['title', 'category_name', 'duration', 'number_of_question', 'created_by', 'status', 'created_at'];
  public dataSource = new MatTableDataSource<ListExams>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  listExam: ListExams[] = [];
  constructor(private http: HttpClient) { }
  ngOnInit() {
    this.getAllOwners();
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }
  public getAllOwners = () => {
    this.http.get<ListExams[]>('http://localhost:3000/listExams')
    .subscribe(listExam => {
      this.listExam = listExam;
      this.dataSource.data = listExam as ListExams[];
    });
  }
  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }
}
