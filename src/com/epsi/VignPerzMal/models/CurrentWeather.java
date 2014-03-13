package com.epsi.VignPerzMal.models;

public class CurrentWeather
{
	private String humidity;
	private String pressure;
	private String temp;
	private String temp_unit;
	private TimeOfDay time_of_day;

	public CurrentWeather(String humidity, String pressure, String temp,
			String temp_unit, TimeOfDay time_of_day) {
		super();

		this.humidity = humidity;
		this.pressure = pressure;
		this.temp = temp;
		this.temp_unit = temp_unit;
		this.time_of_day = time_of_day;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
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

	public String getTempUnit() {
		return temp_unit;
	}

	public void setTempUnit(String temp_unit) {
		this.temp_unit = temp_unit;
	}

	public TimeOfDay getTimeOfDay() {
		return time_of_day;
	}

	public void setTimeOfDay(TimeOfDay time_of_day) {
		this.time_of_day = time_of_day;
	}
}