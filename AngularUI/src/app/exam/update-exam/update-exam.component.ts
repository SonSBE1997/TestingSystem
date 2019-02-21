import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';
import { ExamService } from 'src/app/service/examService.service';
import { Exam } from 'src/app/entity/Exam.interface';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-update-exam',
  templateUrl: './update-exam.component.html',
  styleUrls: ['./update-exam.component.css']
})
export class UpdateExamComponent implements OnInit {
  detailExam: Exam;
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
      .subscribe(detailExam => {
        this.detailExam = detailExam;
      });
  }

  approve() {
    if (this.detailExam.status === 'Draft') {
      // console.log('Draft');
      this.examService.approve(this.detailExam.examId).subscribe(
        success => { },
        error => {
          // console.log(error.error.text);
          if (error.error.text === 'Ok') {
            this.detailExam.status = 'Public';
            this.notifierService.notify('success', 'Approve exam successfully', '');
          } else {
            this.notifierService.notify('warning', 'Can not approve this exam: No question in this exam', '');
          }
        }
      );
    }
  }
}
