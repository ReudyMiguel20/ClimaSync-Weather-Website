import {Component} from '@angular/core';
import {User} from "../../interfaces";
import {UserServiceService} from "../user-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent {

  showSpinner = false;

  user: User = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
  }

  constructor(private userService: UserServiceService,
              private router: Router) {
  }

  onSubmit(): void {

    this.userService.createNewUser(this.user).subscribe((data: any) => {
        console.log(data.token);
        localStorage.setItem('firstname', this.user.firstname);
        localStorage.setItem('lastname', this.user.lastname);
        localStorage.setItem('token', data.token);
        console.log(localStorage.getItem('token'));
        this.showSpinner = true;


        setTimeout(() => {
          this.router.navigate(['/redirecting']);
        }, 1500);
      },
      error => console.log(error));
    console.log(this.user);
    this.showSpinner = false;

  }


}
