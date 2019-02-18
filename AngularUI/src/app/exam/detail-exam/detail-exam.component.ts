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
  flag = true;
  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,

  ) {}
  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.http.get<Exam>(`http://localhost:8080/exam/${id}`);
        })
      )
      .subscribe(exam => {
        this.exam = exam;
      });

  }


  export() {
    if (this.exam.examQuestions.length > 0) {
      this.activatedRoute.paramMap.subscribe(params => {
        const id = params.get('id');
        return (window.location.href =`http://localhost:8080/exam/export/${id}`);
      });
    } else {
      this.flag = false;
    }
  }

  approve() {
    if (this.exam.status === 'Draft') {
      // console.log('Draft');
      this.http
        .put('http://localhost:8080/exam/approve', {
          examId: this.exam.examId
        })
        .subscribe(
          success => {},
          error => {
            // console.log(error.error.text);
            if (error.error.text === 'Ok') {
              this.exam.status = 'Public';
            }
          }
        );
    }
  }
}
