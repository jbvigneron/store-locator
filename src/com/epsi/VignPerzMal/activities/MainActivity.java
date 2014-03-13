package com.epsi.VignPerzMal.activities;

import java.util.AbstractList;
import java.util.ArrayList;

import com.epsi.VignPerzMal.adapters.StoreAdapterProvider;
import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreConstants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {

	private TextView etZipCode;
	private ImageButton btnSearch;
	private ImageButton btnAroundMe;
	private Button btnMap;
	private ListView lvStores;
	
	private DAL<Store> dal;
	private AbstractList<Store> stores;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		dal = new StoreDAL(getApplicationContext());
		stores = new ArrayList<Store>();

		// Get controls
		etZipCode = (TextView)findViewById(R.id.etZipCode);
		btnSearch = (ImageButton)findViewById(R.id.btnSearch);
		btnAroundMe = (ImageButton)findViewById(R.id.btnAroundMe);
		btnMap = (Button)findViewById(R.id.btnMap);
		lvStores = (ListView)findViewById(R.id.lvStores);

		// Create events
		btnSearch.setOnClickListener(this);
		btnAroundMe.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		lvStores.setOnItemClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		stores = dal.get();
		
		displayStores();
	}

	@Override
	public void onClick(View v) {
		if(v == btnSearch)
			searchbyZipCode();
		else if(v == btnAroundMe)
			searchAroundMe();
		else if(v == btnMap)
			showMap();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		ListView v = (ListView)parent;
		Store selStore = (Store)v.getItemAtPosition(position);
		
		Intent in = new Intent(getApplicationContext(), DisplayStoreActivity.class);
		in.putExtra(StoreConstants.STORE, selStore);
		startActivity(in);
	}
	
	private void displayStores() {
		StoreAdapterProvider adapterProvider = new StoreAdapterProvider();
		ListAdapter adapter = adapterProvider.adapt(getApplicationContext(), stores);
		lvStores.setAdapter(adapter);
	}
	
	private void searchbyZipCode() {
		
		String zipCode = etZipCode.getText().toString();
		
		if(zipCode.length() == 5)
			stores = dal.search(zipCode);
		else if(zipCode.length() == 0)
			stores = dal.get();
		else
			Toast.makeText(getApplicationContext(), R.string.message_enter_zip_code,
					   Toast.LENGTH_LONG).show();
		
		displayStores();
	}
	
	private void searchAroundMe() {
		
		// TODO: Launch search around the user
	}
	
	private void showMap() {
		
		// TODO: Show stores on Google Maps
	}
}