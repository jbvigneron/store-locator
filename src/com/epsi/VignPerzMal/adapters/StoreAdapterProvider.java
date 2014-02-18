package com.epsi.VignPerzMal.adapters;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.epsi.VignPerzMal.models.Store;
import com.epsi.VignPerzMal.parser.JsonTags;
import com.epsi.VignPerzMal.storelocator.R;

public class StoreAdapterProvider {

	/**
	 * Transform stores into adapters to be shown in ListViews
	 * @param context The context where the adapter will be shown
	 * @param stores Entities to transform
	 * @return An adapter to be shown in a ListView for example
	 */
	public ListAdapter adapt(Context context, AbstractList<Store> stores) {

		AbstractList<Map<String, String>> results = new ArrayList<Map<String,String>>();

		for(Store store : stores) {
			HashMap<String, String> result = new HashMap<String, String>();

			// adding each child node to HashMap key => value
			result.put(JsonTags.TAG_CODEMAG, store.getCodeMag());
			result.put(JsonTags.TAG_NAME, store.getName());
			result.put(JsonTags.TAG_ADDRESS, store.getAddress());
			result.put(JsonTags.TAG_PHONE, store.getPhone());

			// adding contact to contact list
			results.add(result);
		}
		
		String[] from = new String[] { JsonTags.TAG_NAME, JsonTags.TAG_ADDRESS, JsonTags.TAG_PHONE };
		int[] to = new int[] { R.id.libelle, R.id.adresse, R.id.phone };
		
		ListAdapter adapter = new SimpleAdapter(context, results, R.layout.list_item, from, to);

		return adapter;
	}
}