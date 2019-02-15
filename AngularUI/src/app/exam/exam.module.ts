import { ListExamModule } from './list-exam/list-exam.module';
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
import {MatFormFieldModule, MatInputModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatStepperModule,
} from '@angular/material';
@NgModule({
  declarations: [
    ListExamComponent,
    CreateExamComponent,
    UpdateExamComponent,
    DetailExamComponent,
    UpdateCommonComponent,
    UpdateContentComponent
  ],
  imports: [
    ListExamModule,
    CommonModule,
    ExamRoutingModule,
    CKEditorModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class ExamModule {}
