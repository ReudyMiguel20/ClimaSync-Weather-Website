import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {User} from "../interfaces";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  readonly baseUrl = "http://localhost:8080/api/auth/register";

  constructor(private httpClient: HttpClient) { }

  createNewUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, user);
  }
}
