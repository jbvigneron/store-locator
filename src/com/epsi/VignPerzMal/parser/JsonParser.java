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

				String codeMag = storeJSON.getString(JsonTags.TAG_CODEMAG);
				String name = storeJSON.getString(JsonTags.TAG_NAME);
				String address = storeJSON.getString(JsonTags.TAG_ADDRESS);
				String zipCode = storeJSON.getString(JsonTags.TAG_ZIPCODE);
				String city = storeJSON.getString(JsonTags.TAG_CITY);
				String schedule = storeJSON.getString(JsonTags.TAG_SCHEDULE);
				String phone = storeJSON.getString(JsonTags.TAG_PHONE);
				String fax = storeJSON.getString(JsonTags.TAG_FAX);
				String longitude = storeJSON.getString(JsonTags.TAG_LONGITUDE);
				String latitude = storeJSON.getString(JsonTags.TAG_LATITUDE);

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