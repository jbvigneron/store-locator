package com.epsi.VignPerzMal.models;

import java.util.List;

public class Night extends TimeOfDay {
	public Night(String weather_code, String weather_text, List<Wind> winds) {
		super(weather_code, weather_text, winds);
	}
}