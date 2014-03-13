package com.epsi.VignPerzMal.models;

import java.util.Date;
import java.util.List;

public class Forecast {
	
	private Date date;
	private List<Day> days;
	private String day_max_temp;
	private List<Night> nights;
	private String night_min_temp;
	private String temp_unit;
	
	public Forecast(Date date, List<Day> day, String day_max_temp,
			List<Night> night, String night_min_temp, String temp_unit) {
		super();
		this.date = date;
		this.days = day;
		this.day_max_temp = day_max_temp;
		this.nights = night;
		this.night_min_temp = night_min_temp;
		this.temp_unit = temp_unit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> day) {
		this.days = day;
	}

	public String getDayMaxTemp() {
		return day_max_temp;
	}

	public void setDayMaxTemp(String day_max_temp) {
		this.day_max_temp = day_max_temp;
	}

	public List<Night> getNights() {
		return nights;
	}

	public void setNights(List<Night> night) {
		this.nights = night;
	}

	public String getNightMinTemp() {
		return night_min_temp;
	}

	public void setNightMinTemp(String night_min_temp) {
		this.night_min_temp = night_min_temp;
	}

	public String getTempUnit() {
		return temp_unit;
	}

	public void setTempUnit(String temp_unit) {
		this.temp_unit = temp_unit;
	}
}