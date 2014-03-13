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

		AsyncTask<Void, Void, Void> task = new LoadStoresAsyncTask();
		task.execute();
	}	

	/***
	 * Asynchronous task which retrieve stores from JSON if network is available
	 * or from database if not
	 */
	class LoadStoresAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		/***
		 * Retrieve stores from JSON feed if network is available or from database if not
		 */
		protected Void doInBackground(Void... params) {

			DAL<Store> dal = new StoreDAL(getApplicationContext()); 

			// If network is available, retrieve stores from remote JSON feed
			if(NetworkInfoHelper.isNetworkAvailable(getApplicationContext())) {
				StoreParserFacade facade = new StoreParserFacade();
				AbstractList<Store> storesJSON = facade.get();

				if(storesJSON != null && storesJSON.size() > 0) {
					dal.clear();
					dal.insert(storesJSON);
				}
			}
			
			return null;
		}

		/***
		 * Action is finished. Display the main activity.
		 */
		protected void onPostExecute(Void result) {
			Intent i = new Intent();
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
		}
	}
}