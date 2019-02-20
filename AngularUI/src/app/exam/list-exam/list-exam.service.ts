import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {map} from 'rxjs/operators';
import { Exam } from 'src/app/entity/Exam.interface';

@Injectable()
export class ListExamService {

  constructor(private http: HttpClient) {}
listExam: Exam[] = [];

  public findExams(
        sortOrder = 'ASC',
        pageNumber = 0, pageSize = 3):  Observable<Exam[]> {

        return this.http.get('http://localhost:8080/exam/listExams/pagination', {
            params: new HttpParams()
                .set('sortOrder', sortOrder)
                .set('pageNumber', pageNumber.toString())
                .set('pageSize', pageSize.toString())
        }).pipe(
            map(res =>  res['payload'])
        );
    }
}
