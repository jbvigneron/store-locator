package com.epsi.VignPerzMal.storesparser;

import java.util.AbstractList;

import com.epsi.VignPerzMal.helpers.JsonDownloader;
import com.epsi.VignPerzMal.model.Store;

public class StoreParserFacade {
	
	public AbstractList<Store> get() {
		JsonDownloader downloader = new JsonDownloader();
		String feed = downloader.downloadFeed(StoreConstants.URL);
		
		StoreParser parser = new StoreParser();
		AbstractList<Store> stores = parser.parse(feed);
		
		return stores;
	}
}