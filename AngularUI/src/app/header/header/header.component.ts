import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private route: Router) {}

  ngOnInit() {}

  signOut() {
    localStorage.setItem('isAuthen', 'false');
    this.route.navigate(['/home']);
    window.location.reload();
  }
}
