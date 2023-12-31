import {Component, EventEmitter, Output} from '@angular/core';
import {User} from "../../interfaces";
import {UserServiceService} from "../user-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent {

  constructor(private userService: UserServiceService,
              private router: Router) {
  }

  showSpinner = false;

  user: User = {
    first_name: '',
    last_name: '',
    email: '',
    password: ''
  };

  onSubmit(): void {

    this.userService.createNewUser(this.user).subscribe((data: any) => {
        console.log(data.token);
        localStorage.setItem('first_name', this.user.first_name);
        localStorage.setItem('last_name', this.user.last_name);
        localStorage.setItem('token', data.token);
        console.log(localStorage.getItem('token'));
        this.showSpinner = true;

        setTimeout(() => {
          this.router.navigate(['/registration-successful'])
        }, 3000);
      },
      error => console.log(error));
    console.log(this.user);
    this.showSpinner = false;
  }


}
