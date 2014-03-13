package com.epsi.VignPerzMal.storesparser;

import java.util.AbstractList;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.epsi.VignPerzMal.model.Store;

class StoreParser {

	public AbstractList<Store> parse(String stringToParse) {
		Log.d("Response: ", "> " + stringToParse);

		AbstractList<Store> stores = null;

		try {
			JSONObject jsonObj = new JSONObject(stringToParse);

			// Getting JSON Array node
			JSONObject root =  jsonObj.getJSONObject(StoreConstants.STORES);
			JSONArray storesJSON = root.getJSONArray(StoreConstants.STORE);

			stores = new ArrayList<Store>();

			// Retrieve stores from feed
			for (int i = 0; i < storesJSON.length(); i++) {
				JSONObject storeJSON = storesJSON.getJSONObject(i);

				String codeMag = storeJSON.optString(StoreConstants.CODEMAG);
				String name = storeJSON.optString(StoreConstants.LABEL);
				String address = storeJSON.optString(StoreConstants.ADDRESS);
				String zipCode = storeJSON.optString(StoreConstants.ZIPCODE);
				String city = storeJSON.optString(StoreConstants.CITY);
				String schedule = storeJSON.optString(StoreConstants.SCHEDULE);
				String phone = storeJSON.optString(StoreConstants.PHONE);
				String fax = storeJSON.optString(StoreConstants.FAX);
				double longitude = storeJSON.optDouble(StoreConstants.LONGITUDE);
				double latitude = storeJSON.optDouble(StoreConstants.LATITUDE);

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