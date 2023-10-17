export interface User {
  first_name: string;
  last_name: string;
  email: string;
  password: string;
}

export interface ExistingUser {
  email: string;
  password: string;
}

export interface CountryAndPlace {
  place: string,
  country: string
}

export interface WeatherCondition {
  main: string,
  description: string,
  temp: number,
  feels_like: number,
  temp_min: number,
  temp_max: number,
  pressure: number,
  humidity: number
}

export interface CurrentWeather {
  location: {
    name: string;
    country: string;
    lat: number;
    lon: number;
    country_code: string;
  };
  weather_condition: {
    main: string;
    description: string;
  };
  temp: number;
  feels_like: number;
  temp_min: number;
  temp_max: number;
  pressure: number;
  humidity: number;
  wind_speed: number;
}
