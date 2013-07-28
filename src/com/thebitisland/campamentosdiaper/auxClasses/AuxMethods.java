package com.thebitisland.campamentosdiaper.auxClasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class AuxMethods {

	
	
	public static boolean checkInternetConnection(Context ctx) {

		ConnectivityManager conn = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = conn.getActiveNetworkInfo();
		/* No Internet? Show dialog and Intent to system's settings */
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
			return true;
		}

		Toast.makeText(ctx, "No Internet connection. Enjoy a local use!",
				Toast.LENGTH_SHORT).show();
		
		return false;
	}
}
