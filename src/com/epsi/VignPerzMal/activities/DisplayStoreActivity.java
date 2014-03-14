package com.epsi.VignPerzMal.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.epsi.VignPerzMal.forecast.ForecastFacade;
import com.epsi.VignPerzMal.model.CurrentWeather;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreConstants;

public class DisplayStoreActivity extends Activity implements OnClickListener {

	private TextView tvStoreName;
	private TextView tvAddress;
	private TextView tvPhoneNumber;
	private ImageButton imgVoiceCall;
	private TextView tvSchedule;
	private TextView tvForeCast;
	private ProgressBar pbForecast;


	private Store store;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_display_store);
		setTitle(R.string.title_activity_display_store);

		// Get all activity controls
		tvStoreName = (TextView) findViewById(R.id.tvStoreName);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber);
		imgVoiceCall = (ImageButton) findViewById(R.id.imgVoiceCall);
		tvSchedule = (TextView) findViewById(R.id.tvSchedule);
		tvForeCast = (TextView) findViewById(R.id.tvForecast);
		pbForecast = (ProgressBar) findViewById(R.id.pbForecast);

		// Create events
		imgVoiceCall.setOnClickListener(this);
	}

	@Override
	protected void onStart() {

		super.onStart();

		// Retrieve values from previous intent
		Intent in = getIntent();
		store = in.getParcelableExtra(StoreConstants.STORE);

		tvStoreName.setText(store.getName());
		tvAddress.setText(store.getAddress()+", "+store.getZipCode()+" "+store.getCity());
		tvPhoneNumber.setText(store.getPhone());
		tvSchedule.setText(store.getSchedule().replace("<br />", "\n"));

		AsyncTask<Double, Void, CurrentWeather> task = new ForecastAsyncTask();
		task.execute(store.getLatitude(), store.getLongitude());
	}

	@Override
	public void onClick(View v) {
		if(v == imgVoiceCall) {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:" + tvPhoneNumber.getText()));
			startActivity(callIntent);
		}
	}

	class ForecastAsyncTask extends AsyncTask<Double, Void, CurrentWeather> {

		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			
			pbForecast.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected CurrentWeather doInBackground(Double... params) {
			ForecastFacade facade = new ForecastFacade();
			CurrentWeather weather = facade.get(params[0], params[1]);
			return weather;
		}

		@Override
		protected void onPostExecute(CurrentWeather result) {
			tvForeCast.setText(result.toString());
			pbForecast.setVisibility(View.GONE);
		}
	}
}