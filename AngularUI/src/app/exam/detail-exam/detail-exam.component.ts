import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { DetailExam } from './detailExam.interface';

@Component({
  selector: 'app-detail-exam',
  templateUrl: './detail-exam.component.html',
  styleUrls: ['./detail-exam.component.css']
})
export class DetailExamComponent implements OnInit {
  detailExam: DetailExam;

  constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.activatedRoute.paramMap.pipe(
      mergeMap(
        params => {
          const id = params.get('id');
          return this.http.get<DetailExam>(`http://localhost:3000/exams/${id}`);
        }
      )
    ).subscribe(detailExam => {
      this.detailExam = detailExam;
      console.log(this.detailExam);
    });
  }

  export(){
    window.location.href = "http://localhost:8080/report/java001";
  }
}
