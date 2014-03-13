package com.epsi.VignPerzMal.model;

import java.util.AbstractList;

public class CurrentWeather
{
	private int humidity;
	private int pressure;
	private int temp;
	private String temp_unit;
	private int weather_code;
	private String weather_text;
	private AbstractList<Wind> winds;

	public CurrentWeather(int humidity, int pressure, int temp, String temp_unit,
			int weather_code, String weather_text, AbstractList<Wind> winds) {
		super();

		this.humidity = humidity;
		this.pressure = pressure;
		this.temp = temp;
		this.temp_unit = temp_unit;
		this.weather_code = weather_code;
		this.weather_text = weather_text;
		this.winds = winds;
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
	
	public AbstractList<Wind> getWinds() {
		return winds;
	}
	
	public void setWinds(AbstractList<Wind> winds) {
		this.winds = winds;
	}

	@Override
	public String toString() {
		return temp + "°" + temp_unit.toUpperCase() + " - " + weather_text;
	}
}