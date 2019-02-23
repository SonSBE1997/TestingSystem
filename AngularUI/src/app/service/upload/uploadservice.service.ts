import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs//Observable';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UploadserviceService {

  constructor(private http: HttpClient, private router: Router) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('files', file);
    const req = new HttpRequest('POST', 'http://localhost:8080/uploadfile', formdata, {

    }
    );
    return this.http.request(req);
  }

  importToServer(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('multipartFile', file);
    const req = new HttpRequest('POST', 'http://localhost:8080/exam/import-excel-file', formdata, {

    });
    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get('http://localhost:8080/getallfiles');
  }

}
