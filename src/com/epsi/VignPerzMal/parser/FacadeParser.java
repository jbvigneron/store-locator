package com.epsi.VignPerzMal.parser;

import java.util.AbstractList;

import com.epsi.VignPerzMal.models.Store;

public class FacadeParser {
	
	public AbstractList<Store> parse() {
		JsonDownloader downloader = new JsonDownloader();
		String feed = downloader.downloadFeed();
		
		JsonParser parser = new JsonParser();
		AbstractList<Store> stores = parser.parse(feed);
		
		return stores;
	}
}