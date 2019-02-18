import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Exam } from '../entity/Exam.interface';
import { Question } from '../entity/Question.interface';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExamService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  // Get Detail Exam
  getExamById(id: string): Observable<Exam> {
    return this.http.get<Exam>(this.url + `exam/${id}`);
  }

  // Remove Question
  removeListQuestion(exam): Observable<Object> {
    return this.http.put(this.url + 'exam/remove-question', exam);
  }

  // randomQuestion
  randomQuestion(data): Observable<Object> {
    return this.http.post(this.url + 'exam/random-question', data);
  }

  addListQuestionToExam(data): Observable<Object> {
    return this.http.post(this.url + 'exam/add-question', data);
  }
  // QUESTION
  // getAllQuestion
  getQuestionPaging(): Observable<Question[]> {
    return this.http.get<Question[]>(this.url + `question/all`);
  }
  // ==========QUESTION=============
  // get question ang pagination
  getQuestions(p: string, s: string): Observable<Question[]> {
    return this.http
      .get<Question[]>(this.url + `question/pagination?page=${p}&size=${s}`)
      .pipe(tap());
  }
  // get question sum
  getQuestionSum(): Observable<HttpResponse<Object>> {
    return this.http
      .get<HttpResponse<Object>>(this.url + `question/sum`, {
        observe: 'response'
      })
      .pipe(tap(resp => resp.headers.get('SumQuestion')));
  }
}
