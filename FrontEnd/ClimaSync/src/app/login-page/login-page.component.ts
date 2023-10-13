import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {ExistingUser} from "../../interfaces";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  existingUser: ExistingUser = {
    email: '',
    password: ''
  }

}
