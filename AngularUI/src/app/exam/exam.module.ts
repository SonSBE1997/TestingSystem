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
import { MatFormFieldModule, MatInputModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotifierModule } from 'angular-notifier';

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
  MatStepperModule
} from '@angular/material';
import { TabQuestionComponent } from './update-content/tab-question/tab-question.component';
@NgModule({
  declarations: [
    ListExamComponent,
    CreateExamComponent,
    UpdateExamComponent,
    DetailExamComponent,
    UpdateCommonComponent,
    UpdateContentComponent,
    TabQuestionComponent
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
    NotifierModule.withConfig({
      position: {
        horizontal: {
          /*
           //* Defines the horizontal position on the screen
           * @type {'left' | 'middle' | 'right'}
           */
          position: 'right',

          /**
           * Defines the horizontal distance to the screen edge (in px)
           //* @type {number}
           */
          distance: 20
        },

        vertical: {
          /**
           * Defines the vertical position on the screen
           //* @type {'top' | 'bottom'}
           */
          position: 'top',

          /**
           * Defines the vertical distance to the screen edge (in px)
           // * @type {number}
           */
          distance: 150,

          /**
           * Defines the vertical gap, existing between multiple notifications (in px)
           //* @type {number}
           */
          gap: 10
        }
      },
      behaviour: {
        /**
         * Defines whether each notification will hide itself automatically after a timeout passes
         //* @type {number | false}
         */
        autoHide: 3000,

        /**
         * Defines what happens when someone clicks on a notification
         //* @type {'hide' | false}
         */
        onClick: 'hide',

        /**
         * Defines what happens when someone hovers over a notification
         //* @type {'pauseAutoHide' | 'resetAutoHide' | false}
         */
        onMouseover: 'pauseAutoHide',

        /**
         * Defines whether the dismiss button is visible or not
         //* @type {boolean}
         */
        showDismissButton: true,

        /**
         * Defines whether multiple notification will be stacked, and how high the stack limit is
         //* @type {number | false}
         */
        stacking: 4
      }
    })
  ]
})
export class ExamModule {}
