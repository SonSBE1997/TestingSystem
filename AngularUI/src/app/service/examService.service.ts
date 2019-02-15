import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Exam } from '../entity/Exam.interface';

@Injectable({
  providedIn: 'root'
})
export class ExamService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  // Get Detail Exam
  getExamById(id: string): Observable<Exam> {
    return this.http.get<Exam>(`http://localhost:8080/exam/${id}`);
  }
}
