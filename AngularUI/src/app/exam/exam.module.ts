import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRoutingModule } from './exam-routing.module';
import { ListExamComponent } from './list-exam/list-exam.component';
import { CreateExamComponent } from './create-exam/create-exam.component';
import { UpdateExamComponent } from './update-exam/update-exam.component';
import { DetailExamComponent } from './detail-exam/detail-exam.component';

@NgModule({
  declarations: [ListExamComponent, CreateExamComponent, UpdateExamComponent, DetailExamComponent],
  imports: [
    CommonModule,
    ExamRoutingModule
  ]
})
export class ExamModule { }
