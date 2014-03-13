package com.epsi.VignPerzMal.activities;

import java.util.AbstractList;

import com.epsi.VignPerzMal.adapters.StoreAdapterProvider;
import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tvZipCode;
	private ImageButton btnSearch;
	private ImageButton btnAroundMe;
	private Button btnMap;
	private ListView lvStores;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Get controls
		tvZipCode = (TextView)findViewById(R.id.tvZipCode);
		btnSearch = (ImageButton)findViewById(R.id.btnSearch);
		btnAroundMe = (ImageButton)findViewById(R.id.btnAroundMe);
		btnMap = (Button)findViewById(R.id.btnMap);
		lvStores = (ListView)findViewById(R.id.lvStores);

		// Create events
		btnSearch.setOnClickListener(this);
		btnAroundMe.setOnClickListener(this);
		btnMap.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		// Load stores from database
		DAL<Store> dal = new StoreDAL(getApplicationContext());
		AbstractList<Store> stores = dal.get();
		
		StoreAdapterProvider adapterProvider = new StoreAdapterProvider();
		ListAdapter adapter = adapterProvider.adapt(getApplicationContext(), stores);
		lvStores.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if(v == btnSearch) {
			// TODO: Launch search by ZIP Code
		}
		else if(v == btnAroundMe) {
			// TODO: Launch search around the user
		}
		else if(v == btnMap) {
			// TODO: Show stores on Google Maps
		}
	}
}