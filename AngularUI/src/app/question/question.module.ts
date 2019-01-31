import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListQuestionComponent } from './list-question/list-question.component';
import { CreateQuestionComponent } from './create-question/create-question.component';
import { ViewQuestionComponent } from './view-question/view-question.component';
import { EditQuestionComponent } from './edit-question/edit-question.component';
import { ImportListQuestionComponent } from './import-list-question/import-list-question.component';

@NgModule({
  declarations: [
    ListQuestionComponent,
    CreateQuestionComponent,
    ViewQuestionComponent,
    EditQuestionComponent,
    ImportListQuestionComponent
  ],
  imports: [CommonModule],
  exports: [
    ListQuestionComponent,
    CreateQuestionComponent,
    ViewQuestionComponent,
    EditQuestionComponent,
    ImportListQuestionComponent
  ]
})
export class QuestionModule {}
