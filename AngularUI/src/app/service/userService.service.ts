import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../entity/User.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  // Get Detail User
  getExamById(id: string): Observable<User> {
    return this.http.get<User>(this.url + `user/`);
  }
}
