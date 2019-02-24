import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private authentication = new BehaviorSubject<boolean>(false);
  isAuthentication = this.authentication.asObservable();

  constructor() {}

  authentic(isAuthentication: boolean) {
    this.authentication.next(isAuthentication);
  }
}
