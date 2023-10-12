import {Component} from '@angular/core';
import {User} from "../../interfaces";
import {UserServiceService} from "../user-service.service";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent {

  user: User = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
  }

  constructor(private userService: UserServiceService) { }

  onSubmit(): void {
    this.userService.createNewUser(this.user).subscribe((data: any) => {
      console.log(data.token);
      localStorage.setItem('token', data.token);
      console.log(localStorage.getItem('token'));
    },
      error => console.log(error));
    console.log(this.user);
  }

}
