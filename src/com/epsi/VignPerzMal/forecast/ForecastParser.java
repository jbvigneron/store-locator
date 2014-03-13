package com.epsi.VignPerzMal.forecast;

import java.util.AbstractList;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.epsi.VignPerzMal.model.CurrentWeather;
import com.epsi.VignPerzMal.model.Wind;

class ForecastParser {

	public CurrentWeather parse(String stringToParse) {
		Log.d("Response: ", "> " + stringToParse);

		CurrentWeather currentWeather = null;

		try {
			JSONObject jsonObj = new JSONObject(stringToParse);

			// Get first node
			JSONObject root = jsonObj.getJSONObject(ForecastConstants.WEATHER);

			// Get current weather
			JSONArray cwsJSON = root.getJSONArray(ForecastConstants.CURRENT_WEATHER);			

			JSONObject cwJSON = cwsJSON.getJSONObject(0);

			int humidity = cwJSON.getInt(ForecastConstants.HUMIDITY);
			int pressure = cwJSON.getInt(ForecastConstants.PRESSURE);
			int temp = cwJSON.getInt(ForecastConstants.TEMP);
			String temp_unit = cwJSON.getString(ForecastConstants.TEMP_UNIT);
			int weather_code = cwJSON.getInt(ForecastConstants.WEATHER_CODE);
			String weather_text = cwJSON.getString(ForecastConstants.WEATHER_TEXT);

			// Get winds
			AbstractList<Wind> winds = null;
			JSONArray wsJSON = cwJSON.getJSONArray(ForecastConstants.WINDS);

			winds = new ArrayList<Wind>();

			for(int j = 0; j < wsJSON.length(); j++) {

				JSONObject wJSON = wsJSON.getJSONObject(j);

				String dir = wJSON.getString(ForecastConstants.DIR);
				int dir_degree = wJSON.getInt(ForecastConstants.DIR_DEGREE);
				int speed = wJSON.getInt(ForecastConstants.SPEED);
				String wind_unit = wJSON.getString(ForecastConstants.WIND_UNIT);

				Wind wind = new Wind(dir, dir_degree, speed, wind_unit);
				winds.add(wind);
			}

			currentWeather = new CurrentWeather(humidity, pressure, temp, temp_unit, weather_code, weather_text, winds);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return currentWeather;
	}
}