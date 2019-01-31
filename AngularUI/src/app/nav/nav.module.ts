import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavComponent } from './nav/nav.component';
import { RouterModule } from '@angular/router';
import { QuestionRoutingModule } from '../question/question-routing.module';

@NgModule({
  declarations: [NavComponent],
  imports: [
    CommonModule,
    RouterModule,
    QuestionRoutingModule
  ],
  exports: [NavComponent]
})
export class NavModule { }
