package com.epsi.VignPerzMal.model;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class gMap implements LocationListener, OnMapLongClickListener, OnInfoWindowClickListener {

	private Context context;

	static final LatLng PARIS = new LatLng(48.856614,2.3522219000000177);

	public gMap(Context ctx , GoogleMap pMap)
	{
		this.context = ctx;
	}

	public void setStoresOnMap(GoogleMap map, AbstractList<Store> allstores)
	{
		map.clear();
		LatLngBounds.Builder bld = new LatLngBounds.Builder();

		if(allstores != null) {
			for(Store store : allstores) {
				LatLng magLoc = new LatLng(store.getLatitude() , store.getLongitude());
				map.addMarker(new MarkerOptions()
				.position(magLoc)
				.title(store.getName())
				.snippet(store.getPhone())
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

				 map.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
	                {
					 
						@Override
						public void onInfoWindowClick(Marker m) {
							// TODO Auto-generated method stub
							// Go to Google Map using GPS
							
							double latDest = m.getPosition().latitude;
							double longDest = m.getPosition().longitude;
							Location localLoc = getMyLocation();
							String url = "http://maps.google.com/maps?saddr="+localLoc.getLatitude()+","+localLoc.getLongitude()+"&daddr="+latDest+","+longDest+"&mode=driving";
							Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(url));
							context.startActivity(intent);
						}

	                }); 
				//add marquers to calculate bounds of the map
				LatLng ll = new LatLng(store.getLatitude(), store.getLongitude());
				bld.include(ll);   

			}
		}

		//center map with bounds
		LatLngBounds bounds = bld.build();          
		map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 70));
		//  map.getViewTreeObserver().removeGlobalOnLayoutListener(this);


	}

	public Location getMyLocation()
	{
		LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		boolean enabledGPS = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean enabledWiFi = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		if(enabledWiFi)
		{
			// Check if enabled and if not send user to the GSP settings
			// Better solution would be to display a dialog and suggesting to 
			// go to the settings
			if (!enabledGPS) {
				//Toast.makeText(context, "GPS signal not found", Toast.LENGTH_SHORT).show();
				//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				//startActivity(intent);
			}


			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			// Define the criteria how to select the location provider -> use
			// default
			Criteria criteria = new Criteria();
			String provider = locationManager.getBestProvider(criteria, false);
			Location myLocation = locationManager.getLastKnownLocation(provider);



			// Initialize the location fields
			if (myLocation != null) {
				//   Toast.makeText(this, "Selected Provider " + provider,
				//  Toast.LENGTH_SHORT).show();
				onLocationChanged(myLocation);
			} else {

				//do something
			}
			String test = "Dernière position connue:" + getAddress(myLocation.getLatitude(),myLocation.getLongitude());
			Toast.makeText(context, test, Toast.LENGTH_SHORT).show();


			return myLocation;
		}
		else 
		{
			Toast.makeText(context, "pas de reseau", Toast.LENGTH_SHORT).show();
			return null;
		}

	}

	@SuppressWarnings("null")
	public AbstractList<Store> getNearestStoresOnMap( AbstractList<Store> allstores)
	{

		Location myLoc = getMyLocation();
		if (myLoc != null)
		{
			Location locationA = new Location("");
			AbstractList<Store> storesList = new ArrayList<Store>();

			if(allstores != null) {
				for(Store store : allstores) {

					if(store != null)
					{
						locationA.setLatitude(store.getLatitude());
						locationA.setLongitude(store.getLongitude());

						float distance = myLoc.distanceTo(locationA) / 1000;

						if (distance < 50.00)
							storesList.add(store);
					}
				}
			}
			return storesList;
		}
		return null;
		

	}


	private  String getAddress(Double lat, Double lng)
	{
		try 
		{
			Geocoder geocoder = new Geocoder(context, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) 
			{
				Address address = addresses.get(0);

				sb.append(address.getLocality()).append(", ");
				sb.append(address.getCountryName());
			}

			String addressString = sb.toString();
			return addressString;
		} 
		catch (IOException e) 
		{	
			return "No Address Found";
		}

	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapLongClick(LatLng arg0) {
		// TODO Auto-generated method stub
	}   	
}
