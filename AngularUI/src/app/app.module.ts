import { ListExamModule } from './exam/list-exam/list-exam.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule } from '@angular/material';
import { HeaderModule } from './header/header.module';
import { NavModule } from './nav/nav.module';
import { FooterModule } from './footer/footer.module';
import { QuestionModule } from './question/question.module';
import { ExamModule } from './exam/exam.module';
import { CategoryModule } from './category/category.module';
import { HttpClientModule } from '@angular/common/http';
<<<<<<< HEAD
=======
import { HttpModule } from '@angular/http';
import { ListExamService } from 'src/app/exam/list-exam/list-exam.service';
>>>>>>> dbf0fd505d58eddd12d2c4a723c9e0d1ea34c1c7
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    NavModule,
    HeaderModule,
    FooterModule,
    QuestionModule,
    ExamModule,
    CategoryModule,
    AppRoutingModule,
    HttpClientModule,
    ListExamModule,
    HttpModule
  ],
  providers: [ListExamService],
  bootstrap: [AppComponent]
})
export class AppModule {}
