import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.css']
})

export class CreateExamComponent implements OnInit {
  public Editor = ClassicEditor;
  examFrm: FormGroup;
  constructor() {
  }
  ngOnInit() {
    this.examFrm = new FormGroup({
      title: new FormControl('', Validators.required),
      examcategory: new FormControl( '', Validators.required),
      numberquestion: new FormControl(),
      duration: new FormControl('', Validators.required),
      status: new FormControl(),
      note: new FormControl()
    });
  }
  onCreate() {
    this.examFrm.reset();
    }
  onReset() {
    this.examFrm.reset();
    }
}
