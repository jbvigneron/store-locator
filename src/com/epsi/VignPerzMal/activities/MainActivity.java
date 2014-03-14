package com.epsi.VignPerzMal.activities;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.TreeMap;

import com.epsi.VignPerzMal.adapters.StoreAdapter;
import com.epsi.VignPerzMal.database.DAL;
import com.epsi.VignPerzMal.database.StoreDAL;
import com.epsi.VignPerzMal.model.Store;
import com.epsi.VignPerzMal.model.gMap;
import com.epsi.VignPerzMal.storelocator.R;
import com.epsi.VignPerzMal.storesparser.StoreConstants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener, OnTouchListener {

	private TextView etZipCode;
	private ImageButton btnSearch;
	private ImageButton btnAroundMe;
	private Button btnMap , btnList;
	private ListView lvStores;
	private GoogleMap map;
	private gMap myMap = new gMap(this, map);
	
	private DAL<Store> dal;
	private AbstractList<Store> stores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		setTitle(R.string.title_activity_main);
		
		dal = new StoreDAL(getApplicationContext());
		stores = new ArrayList<Store>();

		// Get controls
		etZipCode = (TextView)findViewById(R.id.etZipCode);
		btnSearch = (ImageButton)findViewById(R.id.btnSearch);
		btnAroundMe = (ImageButton)findViewById(R.id.btnAroundMe);
		btnMap = (Button)findViewById(R.id.btnMap);
		lvStores = (ListView)findViewById(R.id.lvStores);
		btnList = (Button)findViewById(R.id.btnList);
		
		MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
		map = fragment.getMap();
		map.setMyLocationEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);

		// Create events
		btnSearch.setOnClickListener(this);
		btnAroundMe.setOnClickListener(this);
		btnMap.setOnClickListener(this);
		btnList.setOnClickListener(this);

		btnSearch.setOnTouchListener(this);
		btnAroundMe.setOnTouchListener(this);
		btnMap.setOnTouchListener(this);
		btnList.setOnTouchListener(this);
		
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
		else if(v == btnList)
			showList();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
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
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		ListView v = (ListView)parent;
		Store selStore = (Store)v.getItemAtPosition(position);
		
		Intent in = new Intent(getApplicationContext(), DisplayStoreActivity.class);
		in.putExtra(StoreConstants.STORE, selStore);
		startActivity(in);
	}
	
	private void displayStores() {
		ListAdapter adapter = new StoreAdapter(getApplicationContext(), stores);
		lvStores.setAdapter(adapter);
	}
	
	private void searchbyZipCode() {

		String zipCode = etZipCode.getText().toString();

		if(zipCode.length() == 5)
		{
			stores = dal.search(zipCode);
			myMap.setStoresOnMap(map, stores);
		}
		else if(zipCode.length() == 0)
		{
			stores = dal.get();
			myMap.setStoresOnMap(map, stores);
		}
		else
			Toast.makeText(getApplicationContext(), R.string.message_enter_zip_code,
					Toast.LENGTH_LONG).show();

		displayStores();
	}
	
	private void searchAroundMe() {
		
		TreeMap<Integer,Float> listNear = myMap.getNearestStoresOnMap(dal.get());
		myMap.setStoresOnMap(map , stores);
	}

	private void showMap() {

		ListView listviewLayout =  (ListView) findViewById(R.id.lvStores);
		listviewLayout.setVisibility(View.GONE);
		
		View fragmentLayout = findViewById(R.id.map);
		fragmentLayout.setVisibility(View.VISIBLE);
		
		String zipCode = etZipCode.getText().toString();

		AbstractList<Store> allstores;
		
		if(zipCode.length() == 5)
			allstores = dal.search(zipCode);
		else
			allstores = dal.get();
		
		myMap.setStoresOnMap(map , allstores);
	}

	private void showList() {

		View fragmentLayout =   findViewById(R.id.map);
		fragmentLayout.setVisibility(View.GONE);

		ListView listviewLayout =  (ListView) findViewById(R.id.lvStores);
		listviewLayout.setVisibility(View.VISIBLE);
	}
}