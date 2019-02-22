import { UserService } from './../../service/userService.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Semester } from 'src/app/entity/Semester.interface';
import { User } from 'src/app/entity/User.interface';

@Component({
  selector: 'app-list-semester',
  templateUrl: './list-semester.component.html',
  styleUrls: ['./list-semester.component.css']
})
export class ListSemesterComponent implements OnInit {
  semester: Semester;
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
    return this.userService.getListSemesterByUserId(1).subscribe(semester => {
      this.semester = semester;
  });
  }

}
