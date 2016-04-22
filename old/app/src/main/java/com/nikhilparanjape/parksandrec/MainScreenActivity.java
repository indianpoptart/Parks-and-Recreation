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


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;


public class MainScreenActivity extends AppCompatActivity {
	private WebView mWebview;

	public static final String TAG = "MainScreenActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getSupportActionBar();

		actionBar.setHomeAsUpIndicator(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_menu).color(Color.WHITE).sizeDp(IconicsDrawable.ANDROID_ACTIONBAR_ICON_SIZE_DP).paddingDp(IconicsDrawable.ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP));
		actionBar.setDisplayHomeAsUpEnabled(true);

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
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:2034312755"));
		startActivity(callIntent);
		theButton.setBackgroundResource(R.drawable.applabels);

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


