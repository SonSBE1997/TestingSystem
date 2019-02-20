import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../entity/User.interface';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private url = 'http://localhost:8080/';

  constructor(private http: HttpClient) { }

  // Get Detail User
  getListTestBySemesterCode(id: string): Observable<Object> {
    return this.http.get(this.url + `semester-code/${id}`);
  }
}
