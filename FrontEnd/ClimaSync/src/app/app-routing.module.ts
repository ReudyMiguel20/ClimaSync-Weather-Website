import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import {UserLoginComponent} from "./user-login/user-login.component";
import {RegistrationSuccessfulComponent} from "./registration-successful/registration-successful.component";
import {BrowserModule} from "@angular/platform-browser";
import {HomeComponent} from "./home/home.component";
import {LoginPageComponent} from "./login-page/login-page.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'redirecting', component: RegistrationSuccessfulComponent},
  {path: 'login', component: LoginPageComponent}
  // {path: 'redirecting', component: RegistrationSuccessfulComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
