package com.epsi.VignPerzMal.models;

import java.util.List;

public class CurrentWeather
{
	private String humidity;
	private String pressure;
	private String temp;
	private String temp_unit;
	private String weather_code;
	private String weather_text;
	private List<Wind> wind;

	public CurrentWeather(String humidity, String pressure, String temp,
			String temp_unit, String weather_code, String weather_text,
			List<Wind> wind) {
		super();
		
		this.humidity = humidity;
		this.pressure = pressure;
		this.temp = temp;
		this.temp_unit = temp_unit;
		this.weather_code = weather_code;
		this.weather_text = weather_text;
		this.wind = wind;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure(){
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getTemp_unit() {
		return temp_unit;
	}
	public void setTemp_unit(String temp_unit) {
		this.temp_unit = temp_unit;
	}
	public String getWeather_code() {
		return weather_code;
	}
	public void setWeather_code(String weather_code) {
		this.weather_code = weather_code;
	}
	public String getWeather_text() {
		return weather_text;
	}
	public void setWeather_text(String weather_text) {
		this.weather_text = weather_text;
	}
	public List<Wind> getWind() {
		return wind;
	}
	public void setWind(List<Wind> wind) {
		this.wind = wind;
	}
}