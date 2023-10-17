import {Component, Input} from '@angular/core';
import {CurrentWeather} from "../../../../interfaces";

@Component({
  selector: 'app-weathercondition-history',
  templateUrl: './weather-condition-history.component.html',
  styleUrls: ['./weather-condition-history.component.css']
})
export class WeatherConditionHistoryComponent {

  @Input() userCurrentWeatherHistory: CurrentWeather[] = [];

}
