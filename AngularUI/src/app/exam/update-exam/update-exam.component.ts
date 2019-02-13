import { Component, OnInit } from '@angular/core';
import { Exam } from '../update-content/update-content.interface';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-update-exam',
  templateUrl: './update-exam.component.html',
  styleUrls: ['./update-exam.component.css']
})
export class UpdateExamComponent implements OnInit {
  detailExam: Exam;
  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.http.get<Exam>(`http://localhost:8080/exam/${id}`);
        })
      )
      .subscribe(detailExam => {
        this.detailExam = detailExam;
      });
  }

  approve() {
    if (this.detailExam.status === 'Draft') {
      // console.log('Draft');
      this.http
        .put('http://localhost:8080/exam/approve', {
          examId: this.detailExam.examId
        })
        .subscribe(
          success => {},
          error => {
            // console.log(error.error.text);
            if (error.error.text === 'Ok') {
              this.detailExam.status = 'Public';
            }
          }
        );
    }
  }
}
