package com.nikhilparanjape.parksandrec;

/* 
 * Parks and Recreation:
 * 
 * MainScreenActivity.java
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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;
import com.nikhilparanjape.parksandrec.R;

@SuppressLint({ "NewApi", "CutPasteId" })
public class MainScreenActivity extends Activity {
	@SuppressWarnings("unused")
	private WebView mWebview;
	public static final String TAG = "MainScreenActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFBB33")));
		bar.setDisplayShowHomeEnabled(false);
		bar.setTitle("Parks and Rec"); 
		
		setContentView(R.layout.activity_main);
		
		try{
			if(!CheckNetwork.isInternetAvailable(MainScreenActivity.this)){
				TextView t = (TextView)findViewById(R.id.alertDisplay);
				t.setTextColor(Color.RED);
				t.setText("Alert! No Internet Connection!");
			}
		}catch(Exception e){
			TextView t = (TextView)findViewById(R.id.alertDisplay);
			t.setText("Error");
		}
		Button theButton = (Button)findViewById(R.id.schedules);
		theButton.setBackgroundResource(R.drawable.applabels);
		Button agendaBut = (Button)findViewById(R.id.agendaButton);
		agendaBut.setText("Agenda");
		agendaBut.setBackgroundResource(R.drawable.applabels);
		//Button callButton = (Button)findViewById(R.id.callButton);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_refresh:
            rLoad(null);
            return true;
		case R.id.action_settings:
			Intent intent = new Intent(MainScreenActivity.this, SettingsPage.class);
			startActivity(intent);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void openSchedule(View view) 
	{
		Button theButton = (Button)findViewById(R.id.schedules);
		theButton.setBackgroundResource(R.drawable.label_pressed);
		Intent intent = new Intent(MainScreenActivity.this, SchedulePage.class);

		startActivity(intent);
	}
	public void showError(View view){
		TextView t = (TextView)findViewById(R.id.alertDisplay);
		t.setTextColor(Color.RED);
		t.setText("There was a problem loading the agenda!");
	}
	public void openAgenda(View view)
	{

		Button theButton = (Button)findViewById(R.id.agendaButton);
		theButton.setText("Loading...");
		theButton.setBackgroundResource(R.drawable.label_pressed);
		if(!CheckNetwork.isInternetAvailable(MainScreenActivity.this)){
			theButton.setBackgroundResource(R.drawable.applabels);
			theButton.setText("No Internet Connection!");
		}
		else{
			startAgenda(null);
		}


	}
	public void startAgenda(View v){
		try{
			Intent intent = new Intent(MainScreenActivity.this, AgendaPage.class);
			Button agendaBut = (Button)findViewById(R.id.agendaButton);
			agendaBut.setText("Agenda");
			startActivity(intent);
		}
		catch(Exception e){
			Toast.makeText(this,"An unknown error has occured!", Toast.LENGTH_LONG).show();
		}
	}
	private ProgressDialog progress;



	public void showLoadingDialog() {

	    if (progress == null) {
	        progress = new ProgressDialog(this);
	        progress.setTitle("Loading");
	        progress.setMessage("Please Wait");
	    }
	    progress.show();
	}

	public void dismissLoadingDialog() {

	    if (progress != null && progress.isShowing()) {
	        dismissLoadingDialog();
	    }
	}
	public String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  String model = Build.MODEL;
		  if (model.startsWith(manufacturer)) {
		    return capitalize(model);
		  } else {
		    return capitalize(manufacturer) + " " + model;
		  }
		}


		private String capitalize(String s) {
		  if (s == null || s.length() == 0) {
		    return "";
		  }
		  char first = s.charAt(0);
		  if (Character.isUpperCase(first)) {
		    return s;
		  } else {
		    return Character.toUpperCase(first) + s.substring(1);
		  }
		} 
	public void callPandr(View arg0) {
		Button theButton = (Button)findViewById(R.id.callButton);
		theButton.setBackgroundResource(R.drawable.label_pressed);
		TelephonyManager tm= (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_NONE){
			Toast.makeText(this, "Your " + getDeviceName() + " doesn't support calling", Toast.LENGTH_LONG).show();
			theButton.setText("Calling Unsupported");
		}
		else{
			try{
				@SuppressWarnings("unused")
				Button call = (Button)arg0.findViewById(R.id.callButton);
				Context context = null;
				@SuppressWarnings("null")
				boolean hasTelephony = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
				if (hasTelephony) {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:2034312755"));
					startActivity(callIntent);
				}
				else {
					Toast.makeText(this, "Your " + getDeviceName() + " doesn't support calling", Toast.LENGTH_LONG).show();
					call.setText("Calling Unsupported");
				}
			}
			catch(Exception e){
				Button b = (Button)arg0.findViewById(R.id.callButton);
				b.setText("Call Error");
				if(getDeviceName().equals("Unknown sdk")){
					Toast.makeText(this, "Emulators do not support calling", Toast.LENGTH_LONG).show();
				}
				else if(getDeviceName().equals("Unknown google_sdk")){
					Toast.makeText(this, "Emulators do not support calling", Toast.LENGTH_LONG).show();
				}
				
			}
		}
		theButton.setBackgroundResource(R.drawable.applabels);

	}
	@SuppressWarnings("unused")
	private StringBuilder getResult(HttpResponse response) throws IllegalStateException, IOException {

		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())), 1024);
		String output;
		while ((output = br.readLine()) != null) 
			result.append(output);
		return result;
	}
	public void rLoad(View view) throws HttpRequestException{
		
		TextView t = (TextView)findViewById(R.id.alertDisplay);
		if(!CheckNetwork.isInternetAvailable(MainScreenActivity.this)){
			t.setTextColor(Color.RED);
			t.setText("Network Disconnected!");
		}   
		else{
			try{
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				Document doc = Jsoup.connect("http://ridgefieldparksandrec.org").get();
				Element alert = doc.select("div.alert").first();
				String res = alert.text();
				String regex = ", click here for details.";
				res = res.replaceAll(regex, "Check the agenda for more information");
				if (res.contains("")){
					t.setTextColor(Color.WHITE);
					t.setText(res);
				}   
				else{
					t.setTextColor(Color.WHITE);
					t.setText("There are currently no alerts");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}catch (NetworkOnMainThreadException e) {
				t.setTextColor(Color.RED);
				t.setText("Network Error!");
			}catch(Exception e){
				t.setTextColor(Color.RED);
				t.setText("Error!");
			}
		}
	}

	private boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onResume() {
		super.onResume();
		// .... other stuff in my onResume ....
		this.doubleBackToExitPressedOnce = false;
		Button theButton = (Button)findViewById(R.id.schedules);
		theButton.setBackgroundResource(R.drawable.applabels);
		Button agendaBut = (Button)findViewById(R.id.agendaButton);
		agendaBut.setBackgroundResource(R.drawable.applabels);
		dismissLoadingDialog();
	}
	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this,"Press back again to exit", Toast.LENGTH_SHORT).show();


	}
}


