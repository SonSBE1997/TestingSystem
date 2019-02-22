import { Component, OnInit } from '@angular/core';
import { Test } from 'src/app/entity/Test.interface';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/service/userService.service';
import { mergeMap } from 'rxjs/operators';
import { User } from 'src/app/entity/User.interface';

@Component({
  selector: 'app-list-test',
  templateUrl: './list-test.component.html',
  styleUrls: ['./list-test.component.css']
})
export class ListTestComponent implements OnInit {
  listTest: Test;
  user: User;
  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userService.getExamById(1).subscribe(user => {
      this.user = user;
    });
    this.activatedRoute.paramMap
      .pipe(
        mergeMap(params => {
          const id = params.get('id');
          return this.userService.getListTestBySemesterIdUserId(1, id);
        })
      )
      .subscribe(listTest => {
        this.listTest = listTest;
      });
  }

}
