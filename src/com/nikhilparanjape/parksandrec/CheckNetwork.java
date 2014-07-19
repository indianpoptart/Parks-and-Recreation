package com.nikhilparanjape.parksandrec;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

/* 
 * Check Network:
 * 
 * CheckNetwork.java
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Nikhil Paranjape
 */

public class CheckNetwork {
	private static final String TAG = CheckNetwork.class.getSimpleName();
	public static boolean isInternetAvailable(Context c)
	{
	    NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
	    c.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

	    if (info == null)
	    {
	         Log.d(TAG,"No Internet Connection");
	         return false; //If there is no active network connection
	    }
	    else
	    {
	        if(info.isConnected())
	        {
	            Log.d(TAG," Internet Available");
	            return true; //If there is an active internet connection
	        }
	        else
	        {
	            Log.d(TAG," internet connection");
	            return true;
	        }

	    }
	}
}
