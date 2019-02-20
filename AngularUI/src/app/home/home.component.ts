import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  image = ['../../assets/image/slider1.png', '../../assets/image/slider2.png', '../../assets/image/slider3.png'];
  currentSlide = 0;

  constructor() {
    this.changeSlide();
  }

  ngOnInit() {
  }

  changeSlide() {
    this.currentSlide++;
    if (this.currentSlide > 3) {
      this.currentSlide = 1;
    }

    console.log(this.currentSlide);

    setTimeout(() => {
      this.changeSlide()
    }, 1500);
  }
}
