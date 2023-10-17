import {Injectable} from '@angular/core';
import {HttpClient, HttpClientModule, HttpHeaders} from '@angular/common/http';
import {CurrentWeather, ExistingUser, User} from "../interfaces";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  //Variables

  readonly baseUrl = "http://localhost:8080/api/auth";
  readonly baseUrlUser = "http://localhost:8080/api/user"

  userToken: string | null = localStorage.getItem('token');

  headers = new HttpHeaders({
    'Authorization': `${this.userToken}`
  })

  userCurrentWeatherHistory: CurrentWeather[] = [];

  // Constructor


  constructor(private httpClient: HttpClient) {
  }


  // Functions


  createNewUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/register`, user);
  }

  loginUser(user: ExistingUser): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/authenticate`, user);
  }

  getUserCurrentWeatherHistory(): Observable<CurrentWeather[]> {
    return this.httpClient.get<CurrentWeather[]>(
      `${this.baseUrlUser}/recent-history`,
      {headers: this.headers}
    )
  }
}
