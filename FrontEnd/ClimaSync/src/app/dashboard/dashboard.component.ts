import { Component } from '@angular/core';
import { WeatherService } from "../weather.service";
import {CountryAndPlace, WeatherCondition} from "../../interfaces";
import {count} from "rxjs";



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private weatherService: WeatherService) {
  }

  showSpinner = false;
  searchSuccessful = false;

  countryAndPlace: CountryAndPlace = {
    place: '',
    country: ''
  }

  weatherCondition: WeatherCondition = {
    main: '',
    description: '',
    temp: 0,
    feels_like: 0,
    temp_min: 0,
    temp_max: 0,
    pressure: 0,
    humidity: 0
  }

  flagImage: string = '';

  grabWeatherCondition(data: any) {
    this.weatherCondition.main = data.weather_condition.main;
    this.weatherCondition.description = data.weather_condition.description;
    this.weatherCondition.temp = data.temp;
    this.weatherCondition.feels_like = data.feels_like;
    this.weatherCondition.temp_min = data.temp_min;
    this.weatherCondition.temp_max = data.temp_max;
    this.weatherCondition.pressure = data.pressure;
    this.weatherCondition.humidity = data.humidity;
  }

  getCurrentWeather(): void {

    this.weatherService.getCurrentWeather(this.countryAndPlace).subscribe((data: any) => {
        this.showSpinner = true;

        setTimeout(() => {
          this.grabWeatherCondition(data);
          this.flagImage = `https://flagsapi.com/${data.location.country_code}/shiny/64.png`;
          console.log(data)
          this.searchSuccessful = true;
          this.showSpinner = false;
        }, 1500);




    },
      error => console.log(error));
      this.searchSuccessful = false;
  }


  protected readonly count = count;
}
