import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { FormBuilder, FormGroup, Validators, FormControl, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Exam } from 'src/app/entity/Exam.interface';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.css']
})

export class CreateExamComponent implements OnInit {
  public Editor = ClassicEditor;
  examFrm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}
    ngOnInit() {
      this.examFrm = this.fb.group({
      title: ['', Validators.required],
      categoryName: ['', Validators.required],
      numberquestion: ['', Validators.required],
      duration: ['', Validators.required],
      status: ['', Validators.required],
      note: ['', Validators.required],
    });
  }
  onCreate() {
    const data = this.examFrm.value;
      const exam: Exam = {
        examId: uuid(),
        ...data
      };
      console.log(data);
      this.http
      .post('http://localhost:8080/exam/create', exam)
      .subscribe(success => this.router.navigateByUrl('/exam'));
    }
  onReset() {
    this.examFrm.reset();
    }
}
