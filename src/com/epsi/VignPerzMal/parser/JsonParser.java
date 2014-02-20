package com.epsi.VignPerzMal.parser;

import java.util.AbstractList;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.epsi.VignPerzMal.models.Store;

class JsonParser {

	public AbstractList<Store> parse(String stringToParse) {
		Log.d("Response: ", "> " + stringToParse);

		AbstractList<Store> stores = null;

		try {
			JSONObject jsonObj = new JSONObject(stringToParse);

			// Getting JSON Array node
			JSONObject root =  jsonObj.getJSONObject("Magasins");
			JSONArray storesJSON = root.getJSONArray("Magasin");

			stores = new ArrayList<Store>();

			// Retrieve stores from feed
			for (int i = 0; i < storesJSON.length(); i++) {
				JSONObject storeJSON = storesJSON.getJSONObject(i);

				String codeMag = storeJSON.isNull(StoreTags.CODEMAG) ? null : storeJSON.getString(StoreTags.CODEMAG);
				String name = storeJSON.isNull(StoreTags.LABEL) ? null : storeJSON.getString(StoreTags.LABEL);
				String address = storeJSON.isNull(StoreTags.ADDRESS) ? null : storeJSON.getString(StoreTags.ADDRESS);
				String zipCode = storeJSON.isNull(StoreTags.ZIPCODE) ? null : storeJSON.getString(StoreTags.ZIPCODE);
				String city = storeJSON.isNull(StoreTags.CITY) ? null : storeJSON.getString(StoreTags.CITY);
				String schedule = storeJSON.isNull(StoreTags.SCHEDULE) ? null : storeJSON.getString(StoreTags.SCHEDULE);
				String phone = storeJSON.isNull(StoreTags.PHONE) ? null : storeJSON.getString(StoreTags.PHONE);
				String fax = storeJSON.isNull(StoreTags.FAX) ? null : storeJSON.getString(StoreTags.FAX);
				double longitude = storeJSON.isNull(StoreTags.LONGITUDE) ? null : storeJSON.getDouble(StoreTags.LONGITUDE);
				double latitude = storeJSON.isNull(StoreTags.LATITUDE) ? null : storeJSON.getDouble(StoreTags.LATITUDE);

				Store store = new Store(codeMag, name, address, zipCode, city, phone, schedule, fax, latitude, longitude);
				stores.add(store);
			}
		}
		catch(JSONException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return stores;
	}
}