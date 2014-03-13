package com.epsi.VignPerzMal.models;

import java.util.List;

public abstract class TimeOfDay {
	
	private String weather_code;
	private String weather_text;
	private List<Wind> winds;
	
	protected TimeOfDay(String weather_code, String weather_text, List<Wind> winds) {
		super();
		
		this.weather_code = weather_code;
		this.weather_text = weather_text;
		this.winds = winds;
	}
	
	public String getWeatherCode() {
		return weather_code;
	}
	public void setWeatherCode(String weather_code) {
		this.weather_code = weather_code;
	}
	public String getWeatherText() {
		return weather_text;
	}
	public void setWeatherText(String weather_text) {
		this.weather_text = weather_text;
	}
	public List<Wind> getWinds() {
		return winds;
	}
	public void setWinds(List<Wind> winds) {
		this.winds = winds;
	}
}