import { UserService } from './../../service/userService.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/entity/User.interface';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { mergeMap } from 'rxjs/operators';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User;
  constructor(
    private activatedRoute: ActivatedRoute,
    private http: HttpClient,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap
    .pipe(
      mergeMap(params => {
        const id = params.get('id');
        return this.userService.getExamById(id);
      })
    )
    .subscribe(user => {
      this.user = user;
    });

  }

}
