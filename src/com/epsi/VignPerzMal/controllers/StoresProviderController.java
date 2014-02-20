package com.epsi.VignPerzMal.controllers;

import java.net.URL;
import java.util.AbstractList;

import android.content.Context;

import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.helpers.NetworkInfoHelper;
import com.epsi.VignPerzMal.models.Store;
import com.epsi.VignPerzMal.parser.FacadeParser;

public class StoresProviderController {

	public AbstractList<Store> retrieve(Context context, URL url) {
		AbstractList<Store> stores;
		DAL<Store> dal;

		/* Check if network is available.
		 * If yes, retrieve stores from remote JSON feed
		 * If not, retrieve stores from database
		 */
		if(NetworkInfoHelper.isNetworkAvailable(context)) {
			FacadeParser facade = new FacadeParser();
			stores = facade.parse(url);

			if(stores != null) {
				dal = new StoreDAL(context);
				dal.open();
				dal.clear();
				
				for(Store store : stores) {
					dal.insert(store);
				}
			}
		}
		else {	
			dal = new StoreDAL(context);
			return dal.get();
		}

		return stores;
	}
}