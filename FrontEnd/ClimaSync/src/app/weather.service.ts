import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CountryAndPlace} from "../interfaces";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private httpClient: HttpClient) { }

  readonly baseUrl = "http://localhost:8080/api/weather";

  userToken: string | null = localStorage.getItem('token'.toString());

  headers = new HttpHeaders({
    'Authorization': `Bearer ${this.userToken}`
  })


  getCurrentWeather(countryAndPlace: CountryAndPlace): Observable<Object> {
    return this.httpClient.get(
      `${this.baseUrl}/current?q=${countryAndPlace.place}&country=${countryAndPlace.country}`,
      { headers: this.headers}
    );
  }


}
