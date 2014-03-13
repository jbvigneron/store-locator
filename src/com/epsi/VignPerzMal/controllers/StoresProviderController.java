package com.epsi.VignPerzMal.controllers;

import java.util.AbstractList;

import android.content.Context;
import android.os.AsyncTask;

import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.helpers.NetworkInfoHelper;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storesparser.StoreParserFacade;

public class StoresProviderController {

	public AbstractList<Store> retrieve(Context context) {
		AbstractList<Store> stores;

		/* Check if network is available.
		 * If yes, retrieve stores from remote JSON feed
		 * If not, retrieve stores from database
		 */
		if(NetworkInfoHelper.isNetworkAvailable(context)) {
			StoreParserFacade facade = new StoreParserFacade();
			stores = facade.get();

			if(stores != null) {
				new SaveStoresAsyncTask().execute(context, stores);
			}
		}
		else {	
			DAL<Store> dal = new StoreDAL(context);
			stores = dal.get();
		}

		return stores;
	}

	/**
	 * A-sync Task to get stores from JSON feed or from database
	 */
	private class SaveStoresAsyncTask extends AsyncTask<Object, Void, AbstractList<Long>> {

		@Override
		protected AbstractList<Long> doInBackground(Object... arg0) {
			DAL<Store> dal = new StoreDAL((Context)arg0[0]);
			dal.clear();

			@SuppressWarnings("unchecked")
			AbstractList<Store> stores = (AbstractList<Store>)arg0[1];
			return dal.insert(stores);
		}
	}
}