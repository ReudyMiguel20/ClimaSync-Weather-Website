import {Component} from '@angular/core';
import {WeatherService} from "../weather.service";
import {CountryAndPlace, WeatherCondition, CurrentWeather} from "../../interfaces";
import {count} from "rxjs";
import {UserServiceService} from "../user-service.service";
import {WeatherConditionHistoryComponent} from "./weathercondition-history/weathercondition-history/weather-condition-history.component";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private weatherService: WeatherService,
              private userService: UserServiceService) {
  }

  ngOnInit(): void {
    this.getUserCurrentWeatherHistory();
  }

  // Variables

  showSpinner = false;
  searchSuccessful = false;

  flagImage: string = '';

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

  userCurrentWeatherHistory: CurrentWeather[] = [];

  userCurrentWeatherHistoryArray: CurrentWeather[] = []

  // Functions

  // Populates the weatherCondition object with data from the API call
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

  // Calls the API and populates the weatherCondition array with data from the API call
  getUserCurrentWeatherHistory(): void {
    this.userService.getUserCurrentWeatherHistory().subscribe(data => {
        this.userCurrentWeatherHistory = data;
        console.log(this.userCurrentWeatherHistory);
      },
      error => console.log(error));
  }


  getCurrentWeather(): void {

    this.weatherService.getCurrentWeather(this.countryAndPlace).subscribe((data: any) => {
        this.showSpinner = true;

        setTimeout(() => {
          this.grabWeatherCondition(data);
          this.flagImage = `https://flagsapi.com/${data.location.country_code}/shiny/64.png`;
          this.searchSuccessful = true;
          this.showSpinner = false;
        }, 1500);

        this.getUserCurrentWeatherHistory();


      },
      error => console.log(error));
    this.searchSuccessful = false;

  }

  protected readonly count = count;

}
