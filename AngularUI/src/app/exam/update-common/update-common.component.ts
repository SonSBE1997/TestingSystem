import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';


import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';

import { Exam } from '../exam.interface';
import { Category } from 'src/app/category/category.interface';
import { ExamModule } from '../exam.module';


@Component({
  selector: 'app-update-common',
  templateUrl: './update-common.component.html',
  styleUrls: ['./update-common.component.css']
})
export class UpdateCommonComponent implements OnInit {

  examFrm: FormGroup;
  exam: Exam;
  category: Category;

  public Editor = ClassicEditor;
  constructor(private activatedRoute: ActivatedRoute, private fb: FormBuilder,
    private http: HttpClient, private router: Router) {}

    ngOnInit() {
      this.examFrm = this.fb.group({
        title: ['', Validators.required],
        CatergoryName: [''],
        numberOfQuestion: [''],
        duration: [''],
        status: [''],
        note: ['']
      })
      this.activatedRoute.paramMap.pipe(
        mergeMap(params =>{
          const examId = params.get('examId');
          console.log(examId);
          return this.http.get<Exam>(`http://localhost:8015/exam/java124`);
        })
      ).subscribe(exam =>{
        this.exam = exam;
        this.examFrm.patchValue(
          {
            title: exam.title,
            CatergoryName: exam.category.categoryName,
            numberOfQuestion: exam.numberOfQuestion,
            duration: exam.duration,
            status: exam.status,
            note: exam.note
          }
        );
        console.log(this.exam);
      })
    }

  onSubmit() {
    const value = this.examFrm.value;
    let exam: Exam;

    this.activatedRoute.paramMap.pipe(
      mergeMap(params =>{
        return this.http.get<Category>("http://localhost:8015/category/SQL");
      })
    ).subscribe(category =>{
      this.category = category;
      console.log(this.category);
      console.log(this.examFrm.value.CatergoryName);
      exam = {
        category: this.category,
        ...value
      }
    })

    this.http.patch(`http://localhost:8015/exam/update/update-common/${this.exam.examId}`,exam)
        .subscribe(() => {
          this.router.navigateByUrl('/exam');
      })
  }
}
