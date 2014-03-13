package com.epsi.VignPerzMal.forecast;

import java.util.Locale;

import com.epsi.VignPerzMal.helpers.JsonDownloader;
import com.epsi.VignPerzMal.model.CurrentWeather;

public class ForecastFacade {

	public CurrentWeather get(float latitude, float longitude) {
		JsonDownloader downloader = new JsonDownloader();

		String url = String.format(Locale.getDefault(), ForecastConstants.URL, latitude, longitude);
		String feed = downloader.downloadFeed(url);

		ForecastParser parser = new ForecastParser();
		CurrentWeather forecast = parser.parse(feed);

		return forecast;
	}
}