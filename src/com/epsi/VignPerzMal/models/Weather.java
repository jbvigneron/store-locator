package com.epsi.VignPerzMal.models;

import java.util.List;

public class Weather {
	
	private List<CurrentWeather> current_weather;
	private List<Forecast> forecast;
	
	public Weather(List<CurrentWeather> current_weather, List<Forecast> forecast) {
		super();
		
		this.current_weather = current_weather;
		this.forecast = forecast;
	}

	public List<CurrentWeather> getCurrentWeather() {
		return current_weather;
	}
	
	public void setCurrentWeather(List<CurrentWeather> current_weather) {
		this.current_weather = current_weather;
	}
	
	public List<Forecast> getForecast() {
		return forecast;
	}
	
	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}
}