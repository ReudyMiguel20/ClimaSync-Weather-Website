import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {ExistingUser, User} from "../interfaces";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  readonly baseUrl = "http://localhost:8080/api/auth";

  constructor(private httpClient: HttpClient) { }

  createNewUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/register`, user);
  }

  loginUser(user: ExistingUser): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/authenticate`, user);
  }
}
