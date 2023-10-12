import {Component} from '@angular/core';
import { User } from "../../User";
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
    this.userService.createNewUser(this.user).subscribe(data => {
      console.log(data);
    },
      error => console.log(error));
    console.log(this.user);
  }

}
