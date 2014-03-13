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

				String codeMag = storeJSON.isNull(StoreConstants.CODEMAG) ? null : storeJSON.getString(StoreConstants.CODEMAG);
				String name = storeJSON.isNull(StoreConstants.LABEL) ? null : storeJSON.getString(StoreConstants.LABEL);
				String address = storeJSON.isNull(StoreConstants.ADDRESS) ? null : storeJSON.getString(StoreConstants.ADDRESS);
				String zipCode = storeJSON.isNull(StoreConstants.ZIPCODE) ? null : storeJSON.getString(StoreConstants.ZIPCODE);
				String city = storeJSON.isNull(StoreConstants.CITY) ? null : storeJSON.getString(StoreConstants.CITY);
				String schedule = storeJSON.isNull(StoreConstants.SCHEDULE) ? null : storeJSON.getString(StoreConstants.SCHEDULE);
				String phone = storeJSON.isNull(StoreConstants.PHONE) ? null : storeJSON.getString(StoreConstants.PHONE);
				String fax = storeJSON.isNull(StoreConstants.FAX) ? null : storeJSON.getString(StoreConstants.FAX);
				double longitude = storeJSON.isNull(StoreConstants.LONGITUDE) ? null : storeJSON.getDouble(StoreConstants.LONGITUDE);
				double latitude = storeJSON.isNull(StoreConstants.LATITUDE) ? null : storeJSON.getDouble(StoreConstants.LATITUDE);

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