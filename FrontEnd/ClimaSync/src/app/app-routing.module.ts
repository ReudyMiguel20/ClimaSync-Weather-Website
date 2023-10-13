import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {UserLoginComponent} from "./user-login/user-login.component";
import {RegistrationSuccessfulComponent} from "./loading-screens/registration-successful/registration-successful.component";
import {BrowserModule} from "@angular/platform-browser";
import {HomeComponent} from "./home/home.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {LoginSuccessfulComponent} from "./loading-screens/login-successful/login-successful.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'registration-successful', component: RegistrationSuccessfulComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'login-successful', component: LoginSuccessfulComponent}
  // {path: 'redirecting', component: RegistrationSuccessfulComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
