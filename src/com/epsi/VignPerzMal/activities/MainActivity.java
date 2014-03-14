package com.epsi.VignPerzMal.activities;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

import com.epsi.VignPerzMal.adapters.StoreAdapterProvider;
import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.model.gMap;
import com.epsi.VignPerzMal.storelocator.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener {

	private TextView etZipCode;
	private ImageButton btnSearch;
	private ImageButton btnAroundMe;
	private Button btnMap , btnList;
	private ListView lvStores;
	private GoogleMap map;
	private DAL<Store> dal;
	private AbstractList<Store> stores;
	private gMap myMap = new gMap(this, map);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		map.setMyLocationEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);

		dal = new StoreDAL(getApplicationContext());
		stores = new ArrayList<Store>();

		// Get controls
		etZipCode = (TextView)findViewById(R.id.etZipCode);
		btnSearch = (ImageButton)findViewById(R.id.btnSearch);
		btnAroundMe = (ImageButton)findViewById(R.id.btnAroundMe);
		btnMap = (Button)findViewById(R.id.btnMap);
		lvStores = (ListView)findViewById(R.id.lvStores);
		btnList = (Button)findViewById(R.id.btnList);

		// Create events
		btnSearch.setOnClickListener(this);
		btnAroundMe.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnList.setOnClickListener(this);

		btnSearch.setOnTouchListener(this);
		btnAroundMe.setOnTouchListener(this);
		btnMap.setOnTouchListener(this);
		btnList.setOnTouchListener(this);
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
		{
			showMap();
		}
		else if(v == btnList)
			showList();
	}

	private void displayStores() {
		StoreAdapterProvider adapterProvider = new StoreAdapterProvider();
		ListAdapter adapter = adapterProvider.adapt(getApplicationContext(), stores);
		lvStores.setAdapter(adapter);
	}

	private void searchbyZipCode() {

		String zipCode = etZipCode.getText().toString();

		if(zipCode.length() == 5)
		{
			stores = dal.search(zipCode);
			myMap.setStoresOnMap(map , stores);
		}
		else if(zipCode.length() == 0)
		{
			stores = dal.get();
			myMap.setStoresOnMap(map , stores);
		}
		else
			Toast.makeText(getApplicationContext(), R.string.message_enter_zip_code,
					Toast.LENGTH_LONG).show();

		displayStores();
	}

	private void searchAroundMe() {
		
		TreeMap<Integer,Float> listNear = myMap.getNearestStoresOnMap(dal.get());
		
	
		myMap.setStoresOnMap(map , stores);

		// TODO: Launch search around the user
	}

	private void showMap() {

		// TODO: Show stores on Google Maps
		
		/*hide list and show map*/
		
		ListView listviewLayout =  (ListView) findViewById(R.id.lvStores);
		listviewLayout.setVisibility(View.GONE);
		View fragmentLayout =   findViewById(R.id.map);
		fragmentLayout.setVisibility(View.VISIBLE);
		String zipCode = etZipCode.getText().toString();

		if(zipCode.length() == 5)
		{
			AbstractList<Store> allstores = dal.search(zipCode);
			myMap.setStoresOnMap(map , allstores);
		}
		else
		{
			AbstractList<Store> allstores = dal.get();
			myMap.setStoresOnMap(map , allstores);
		}
	}

	private void showList() {

		// TODO: Show stores on List

		View fragmentLayout =   findViewById(R.id.map);
		fragmentLayout.setVisibility(View.GONE);

		ListView listviewLayout =  (ListView) findViewById(R.id.lvStores);
		listviewLayout.setVisibility(View.VISIBLE);


	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(v == btnSearch)
			searchbyZipCode();
		else if(v == btnAroundMe)
			searchAroundMe();
		else if(v == btnMap)
			showMap();
		else if(v == btnList)
			showList();
		return false;
	}
}