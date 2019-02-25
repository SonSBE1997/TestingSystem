import { Component, OnInit } from '@angular/core';
import { HomeService } from '../service/homeService.service';
import { HttpClient } from '@angular/common/http';
import { SharedService } from '../service/sharedService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  image = [
    '../../assets/image/slider1.png',
    '../../assets/image/slider2.png',
    '../../assets/image/slider3.png'
  ];
  currentSlide = 0;
  tests;
  semesterExamCode = '';
  isAuthentication = false;

  constructor(
    private http: HttpClient,
    private sharedService: SharedService,
    private route: Router
  ) {
    this.changeSlide();
  }

  ngOnInit() {}

  changeSlide() {
    this.currentSlide++;
    if (this.currentSlide > 3) {
      this.currentSlide = 1;
    }

    // console.log(this.currentSlide);

    setTimeout(() => {
      this.changeSlide();
    }, 3000);

    this.sharedService.isAuthentication.subscribe(
      isAuthentication => (this.isAuthentication = isAuthentication)
    );

    const isAuthen = localStorage.getItem('isAuthen');
    // if (isAuthen === 'true') {
    //   this.route.navigate(['/exam']);
    // }
  }

  showSemesterTest() {
    if (this.semesterExamCode !== '') {
      this.http
        .get(`http://localhost:8080/semester-code/${this.semesterExamCode}`)
        .subscribe(ob => {
          console.log(ob);
          this.tests = ob;
        });
    }
  }

  change(e) {
    this.semesterExamCode = e.value;
    console.log(this.semesterExamCode);
  }

  signIn() {
    this.sharedService.authentic(true);
    localStorage.setItem('isAuthen', 'true');
    this.route.navigate(['/exam']);
  }
  signUp() {
    this.route.navigate(['/user']);
  }
}
