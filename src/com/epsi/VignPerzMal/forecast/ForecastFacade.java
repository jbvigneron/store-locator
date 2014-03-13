package com.epsi.VignPerzMal.forecast;

import com.epsi.VignPerzMal.helpers.JsonDownloader;
import com.epsi.VignPerzMal.model.CurrentWeather;

public class ForecastFacade {

	public CurrentWeather get(double latitude, double longitude) {
		JsonDownloader downloader = new JsonDownloader();

		String url = ForecastConstants.URL + latitude + "," + longitude;
		String feed = downloader.downloadFeed(url);

		ForecastParser parser = new ForecastParser();
		CurrentWeather forecast = parser.parse(feed);

		return forecast;
	}
}