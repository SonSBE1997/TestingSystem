import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ListExams } from './listExam.interface';

@Component({
  selector: 'app-list-exam',
  templateUrl: './list-exam.component.html',
  styleUrls: ['./list-exam.component.css']
})
export class ListExamComponent implements OnInit {
  listExam: ListExams[] = [];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<ListExams[]>('http://localhost:3000/listExams')
    .subscribe(listExam => {
      this.listExam = listExam;
    });
  }

}
