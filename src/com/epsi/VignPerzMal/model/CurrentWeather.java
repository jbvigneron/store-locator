package com.epsi.VignPerzMal.model;

import java.util.Locale;

public class CurrentWeather
{
	private int humidity;
	private int pressure;
	private int temp;
	private String temp_unit;
	private int weather_code;
	private String weather_text;
	private Wind wind;

	public CurrentWeather(int humidity, int pressure, int temp, String temp_unit,
			int weather_code, String weather_text, Wind wind) {
		super();

		this.humidity = humidity;
		this.pressure = pressure;
		this.temp = temp;
		this.temp_unit = temp_unit;
		this.weather_code = weather_code;
		this.weather_text = weather_text;
		this.wind = wind;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public int getTemp() {
		return temp;
	}
	
	public void setTemp(int temp) {
		this.temp = temp;
	}

	public String getTempUnit() {
		return temp_unit;
	}

	public void setTempUnit(String temp_unit) {
		this.temp_unit = temp_unit;
	}

	public int getWeatherCode() {
		return weather_code;
	}
	
	public void setWeatherCode(int weather_code) {
		this.weather_code = weather_code;
	}
	
	public String getWeatherText() {
		return weather_text;
	}
	
	public void setWeatherText(String weather_text) {
		this.weather_text = weather_text;
	}
	
	public Wind getWind() {
		return wind;
	}
	
	public void setWinds(Wind wind) {
		this.wind = wind;
	}

	@Override
	public String toString() {
		return temp + "°" + temp_unit.toUpperCase(Locale.getDefault()) + " - " + weather_text;
	}
}