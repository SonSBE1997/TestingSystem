import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExamRoutingModule } from './exam-routing.module';
import { ListExamComponent } from './list-exam/list-exam.component';
import { CreateExamComponent } from './create-exam/create-exam.component';
import { UpdateExamComponent } from './update-exam/update-exam.component';
import { DetailExamComponent } from './detail-exam/detail-exam.component';
import { UpdateCommonComponent } from './update-common/update-common.component';
import { UpdateContentComponent } from './update-content/update-content.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

@NgModule({
  declarations: [
    ListExamComponent,
    CreateExamComponent,
    UpdateExamComponent,
    DetailExamComponent,
    UpdateCommonComponent,
    UpdateContentComponent
  ],
  imports: [CommonModule, ExamRoutingModule, CKEditorModule]
})
export class ExamModule { }
