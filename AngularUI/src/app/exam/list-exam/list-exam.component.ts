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
  showMedia: boolean;
  imageSrc: any;
  file: any;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<ListExams[]>('http://localhost:3000/listExams')
    .subscribe(listExam => {
      this.listExam = listExam;
    });
  }

  onChangeFile(event) {
    if (event.target.files && event.target.files[0]) {
      this.showMedia = false;
      this.file = event.target.files[0];
      const reader = new FileReader();
      reader.onload = e => (this.imageSrc = reader.result);
      reader.readAsDataURL(this.file);
    } else {
      this.imageSrc = '';
    }
  }

}
