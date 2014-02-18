package com.epsi.VignPerzMal.parser;

import java.net.URL;
import java.util.AbstractList;

import com.epsi.VignPerzMal.models.Store;

public class FacadeParser {
	
	public AbstractList<Store> parse(URL url) {
		JsonDownloader downloader = new JsonDownloader();
		String feed = downloader.downloadFeed(url);
		
		JsonParser parser = new JsonParser();
		AbstractList<Store> stores = parser.parse(feed);
		
		return stores;
	}
}