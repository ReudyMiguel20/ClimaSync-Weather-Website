import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-successful',
  templateUrl: './login-successful.component.html',
  styleUrls: ['./login-successful.component.css']
})
export class LoginSuccessfulComponent {

  // @ts-ignore
  firstName: string = localStorage.getItem('first_name').toString();

  constructor(private router: Router) {
  }

  ngOnInit() {
    setTimeout(() => {
      this.router.navigate(['']);
    }, 1800);
  }
}
