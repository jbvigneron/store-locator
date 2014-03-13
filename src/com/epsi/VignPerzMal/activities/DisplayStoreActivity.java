package com.epsi.VignPerzMal.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.forecast.ForecastFacade;
import com.epsi.VignPerzMal.model.CurrentWeather;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreConstants;

public class DisplayStoreActivity  extends Activity implements OnClickListener {

	private TextView tvStoreName;
	private TextView tvAddress;
	private TextView tvPhoneNumber;
	private ImageButton imgVoiceCall;
	private TextView tvForeCast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_single_contact);

		// Get all activity controls
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
		imgVoiceCall = (ImageButton) findViewById(R.id.imgVoiceCall);
		tvForeCast = (TextView) findViewById(R.id.tvForecast);

		// Create events
		imgVoiceCall.setOnClickListener(this);
		
		// Retrieve values from previous intent
		Intent in = getIntent();

		// Get values from precedent intents to set display values
		String label = in.getStringExtra(StoreConstants.LABEL);
		tvStoreName.setText(label);
		
		String address = in.getStringExtra(StoreConstants.ADDRESS);
		tvAddress.setText(address);
		
		String phone = in.getStringExtra(StoreConstants.PHONE);
		tvPhoneNumber.setText(phone);
		
		int id = in.getIntExtra(StoreConstants.ID, 0);
		AsyncTask<Integer, Void, Store> task = new GetStoreFromDbAsyncTask();
		task.execute(id);
	}

	@Override
	public void onClick(View v) {
		// Call image
		if(v == imgVoiceCall) {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + tvPhoneNumber.getText()));
			startActivity(callIntent);
		}
	}

	class GetStoreFromDbAsyncTask extends AsyncTask<Integer, Void, Store> {

		protected void onPreExecute() {
			// TODO: Show loading ring
		}
		
		@Override
		protected Store doInBackground(Integer... params) {
			DAL<Store> dal = new StoreDAL(getApplicationContext());
			
			int id = params[0];
			Store store = dal.get(id);
			
			return store;
		}

		protected void onPostExecute(Store result) {
			AsyncTask<Double, Void, CurrentWeather> task = new ForecastAsyncTask();
			task.execute(result.getLatitude(), result.getLongitude());
		}
	}
	
	class ForecastAsyncTask extends AsyncTask<Double, Void, CurrentWeather> {

		@Override
		protected CurrentWeather doInBackground(Double... params) {
			ForecastFacade facade = new ForecastFacade();
			CurrentWeather weather = facade.get(params[0], params[1]);
			return weather;
		}

		protected void onPostExecute(CurrentWeather result) {
			tvForeCast.setText(result.toString());
			
			// TODO: Hide loading ring
		}
	}
}