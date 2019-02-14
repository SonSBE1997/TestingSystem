import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';


import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';

import { Exam } from '../exam.interface';
import { Category } from 'src/app/category/category.interface';


@Component({
  selector: 'app-update-common',
  templateUrl: './update-common.component.html',
  styleUrls: ['./update-common.component.css']
})
export class UpdateCommonComponent implements OnInit {

  examFrm: FormGroup;
  exam: Exam;
  category: Category;
  categories: Category[] = [];

  inputCategory: string;

  public Editor = ClassicEditor;
  constructor(private activatedRoute: ActivatedRoute, private fb: FormBuilder,
    private http: HttpClient, private router: Router) {}

    onChange(value: string) {
      this.inputCategory = value;
      console.log(this.inputCategory)

      this.activatedRoute.paramMap.pipe(
        mergeMap(params =>{
          return this.http.get<Category>("http://localhost:8015/category/" + this.inputCategory);
        })
      ).subscribe(category =>{
        this.category = category;
        console.log("category1: " + this.category);
      })
    }

    ngOnInit() {

      this.examFrm = this.fb.group({
        title: ['', Validators.required],
        CatergoryName: ['', Validators.required],
        numberOfQuestion: ['', Validators.required],
        duration: ['', Validators.required],
        status: ['', ],
        note: ['']
      })

      this.http.get<Category[]>(`http://localhost:8015/category/list-category`)
      .subscribe(categories => {
        this.categories = categories;
        console.table(this.categories)
      })

      this.activatedRoute.paramMap.pipe(
        mergeMap(params =>{
          const examId = params.get('id');
          console.log(examId);
          return this.http.get<Exam>(`http://localhost:8015/exam/${examId}`);
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
    const exam: Exam = {
        category: this.category,
        ...value
      }
    console.log("category2: " + this.category);
    this.http.put(`http://localhost:8015/exam/update/update-common/${this.exam.examId}`,exam)
        .subscribe(() => {
          this.router.navigateByUrl('/exam');
      })
  }
}
