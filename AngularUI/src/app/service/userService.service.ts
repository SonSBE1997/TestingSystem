import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../entity/User.interface';
import { Semester } from '../entity/Semester.interface';
import { Test } from '../entity/Test.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  // Get Detail User
  getExamById(id: number): Observable<User> {
    return this.http.get<User>(this.url + `user/${id}`);
  }

  getListSemesterByUserId(id: number): Observable<Semester> {
    return this.http.get<Semester>(this.url + `users/${id}/semesters`);
  }

  getListTestBySemesterIdUserId(userId: number, semesterId: string): Observable<Test> {
    return this.http.get<Test>(this.url + `test/users/${userId}/semesters/${semesterId}/tests/`);
  }
}
