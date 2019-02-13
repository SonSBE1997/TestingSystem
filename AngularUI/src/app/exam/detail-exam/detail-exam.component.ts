import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { Exam } from 'src/app/entity/Exam.interface';


@Component({
  selector: 'app-detail-exam',
  templateUrl: './detail-exam.component.html',
  styleUrls: ['./detail-exam.component.css']
})
export class DetailExamComponent implements OnInit {
  exam: Exam;

  constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.activatedRoute.paramMap.pipe(
      mergeMap(
        params => {
          const id = params.get('id');
          return this.http.get<Exam>(`http://localhost:8080/exam/${id}`);
        }
      )
    ).subscribe(exam => {
      this.exam = exam;
      console.log(this.exam);
    });
  }

  export() {
    window.location.href = 'http://localhost:8080/report/java001';
  }
}
