package com.epsi.VignPerzMal.activities;

import java.util.AbstractList;

import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.helpers.NetworkInfoHelper;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreParserFacade;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		AsyncTask<Void, Void, AbstractList<Store>> task = new LoadStoresAsyncTask();
		task.execute();
	}	
	
	/***
	 * Asynchronous task which retrieve stores from JSON if network is available
	 * or from database if not
	 */
	class LoadStoresAsyncTask extends AsyncTask<Void, Void, AbstractList<Store>> {

		@Override
		/***
		 * Retrieve stores from JSON feed if network is available or from database if not
		 */
		protected AbstractList<Store> doInBackground(Void... params) {
			
			AbstractList<Store> stores;
			
			// Check if network is available.
			// If yes, retrieve stores from remote JSON feed
			if(NetworkInfoHelper.isNetworkAvailable(getApplicationContext())) {
				StoreParserFacade facade = new StoreParserFacade();
				stores = facade.get();

				if(stores != null) {
					DAL<Store> dal = new StoreDAL(getApplicationContext());
					dal.clear();

					dal.insert(stores);
				}
			}
			// If not, retrieve stores from database
			else {	
				DAL<Store> dal = new StoreDAL(getApplicationContext());
				stores = dal.get();
			}
			
			return stores;
		}
		
		/***
		 * Action is finished. Display the main activity.
		 */
		protected void onPostExecute(AbstractList<Store> results) {
			Intent i = new Intent();
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
		}
	}
}