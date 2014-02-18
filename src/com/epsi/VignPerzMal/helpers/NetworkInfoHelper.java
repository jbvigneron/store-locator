package com.epsi.VignPerzMal.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkInfoHelper {
	
	/**
	 * A helper which indicates if the network is available
	 * @param context Application context (an Activity for example)
	 * @return A boolean which determines if the network is available
	 */
	public static boolean isNetworkAvailable(Context context) {
		
		ConnectivityManager connectivityManager  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}