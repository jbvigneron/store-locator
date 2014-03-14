package com.epsi.VignPerzMal.model;

import java.io.IOException;
import java.util.AbstractList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class gMap implements LocationListener {

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
		boolean enabledGPS = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean enabledWiFi = service
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		// Check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to 
		// go to the settings
		if (!enabledGPS) {
			Toast.makeText(context, "GPS signal not found", Toast.LENGTH_LONG).show();
			//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			//startActivity(intent);
		}

		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
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
		String test = "Dernière connue:" + getAddress(myLocation.getLatitude(),myLocation.getLongitude());
		Toast.makeText(context, test, Toast.LENGTH_LONG).show();


		return myLocation;

	}

	public TreeMap<Integer,Float> getNearestStoresOnMap( AbstractList<Store> allstores)
	{
		Map<Integer,Float> h = new TreeMap<Integer,Float>();
		Location myLoc = getMyLocation();
		Location locationA = new Location("");
		
		
		if(allstores != null) {
			for(Store store : allstores) {
				 
				 locationA.setLatitude(store.getLatitude());
				 locationA.setLongitude(store.getLongitude());
				 
				 float distance = myLoc.distanceTo(locationA) / 1000;
				 
				 Toast.makeText(context,String.valueOf(distance), Toast.LENGTH_LONG).show();
				 h.put(store.getId(), distance); 
				
			}
		}

		return (TreeMap<Integer, Float>) h;

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

	//	Location sydney = new Location("23 rue du dépot 62000 Arras");

	//String test = getAddress(53.558, 9.927);
	//Toast.makeText(this, test, Toast.LENGTH_LONG).show();

	//float test = map.getMyLocation().distanceTo(sydney);

}
