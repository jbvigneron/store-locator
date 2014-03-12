package com.epsi.VignPerzMal.models;

import java.util.List;

public class Forecast {
	public String date;
    public List<Day> day;
    public String day_max_temp;
    public List<Night> night;
    public String night_min_temp;
    public String temp_unit;
}