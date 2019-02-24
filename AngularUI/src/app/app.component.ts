import { Component, OnInit } from '@angular/core';
import { SharedService } from './service/sharedService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'test-ui';
  isAuthentication: boolean;

  constructor(private sharedService: SharedService, private router: Router) {}

  ngOnInit() {
    this.sharedService.isAuthentication.subscribe(
      isAuthentication => (this.isAuthentication = isAuthentication)
    );

    const isAuthen = localStorage.getItem('isAuthen');
    if (isAuthen !== null) {
      if (isAuthen === 'true') {
        this.sharedService.authentic(true);
      } else {
        this.sharedService.authentic(false);
        this.router.navigate(['/home']);
      }
    } else {
      this.isAuthentication = false;
      localStorage.setItem('isAuthen', this.isAuthentication + '');
      this.router.navigate(['/home']);
    }
  }
}
