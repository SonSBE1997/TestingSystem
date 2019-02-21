import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { mergeMap } from 'rxjs/operators';
import { Exam } from 'src/app/entity/Exam.interface';
import { ExamService } from 'src/app/service/examService.service';
import { NotifierService } from 'angular-notifier';

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
    private examService: ExamService,
    private notifierService: NotifierService
  ) { }
  ngOnInit() {
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.examService.getExamById(id);
        })
      )
      .subscribe(exam => {
        this.exam = exam;
      });

  }


  export() {
    if (this.exam.examQuestions.length > 0) {
      return (window.location.href = this.examService.exportUrl(this.exam.examId));
    } else {
      this.flag = false;
    }
  }

  approve() {
    if (this.exam.status === 'Draft') {
      // console.log('Draft');
      this.examService.approve(this.exam.examId).subscribe(
        success => { },
        error => {
          // console.log(error.error.text);
          if (error.error.text === 'Ok') {
            this.exam.status = 'Public';
            this.notifierService.notify('success', 'Approve exam successfully', '');
          } else {
            this.notifierService.notify('error', 'Can not approve this exam: No question in this exam', '');
          }
        }
      );
    }
  }
}
