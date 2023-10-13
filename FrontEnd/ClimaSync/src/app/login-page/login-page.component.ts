import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {ExistingUser} from "../../interfaces";
import {UserServiceService} from "../user-service.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  showSpinner = false;

  constructor(private router: Router,
              private userService: UserServiceService) {
  }

  existingUser: ExistingUser = {
    email: '',
    password: ''
  }

  protected readonly onsubmit = onsubmit;

  onSubmit() {

    this.userService.loginUser(this.existingUser).subscribe((data: any) => {
        this.showSpinner = true;
        console.log(data.token);
        localStorage.setItem('first_name', data.first_name);
        localStorage.setItem('token', data.token);
        console.log(localStorage.getItem('token'));

        setTimeout(() => {
          this.router.navigate(['/login-successful']);
        }, 1500);
    },
      error => console.log(error));
  }
}
