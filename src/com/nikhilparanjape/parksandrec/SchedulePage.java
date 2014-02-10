package com.nikhilparanjape.parksandrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.nikhilparanjape.parksandrec.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SchedulePage extends Activity {



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9900")));
		bar.setDisplayShowHomeEnabled(false);
		bar.setTitle("Schedules"); 
		setContentView(R.layout.schedule_menu);
	}
	public void showError(View view){
		Toast.makeText(this,"This currently does not work ", Toast.LENGTH_LONG).show();
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
		case R.id.action_settings:
			Intent intent = new Intent(SchedulePage.this, SettingsPage.class);
			startActivity(intent);
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	public void poolSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), "pool.pdf");
		try {
			in = assetManager.open("pool.pdf");
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/pool.pdf"),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void gymSchedule(View arg0) {
		//gym schedule opener
		Toast.makeText(this,"Call before coming to check for availablility", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "gym.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Toast.makeText(this,"Call before coming to check for availablility", Toast.LENGTH_LONG).show();
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void groupSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "group.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void premGroupSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "prem.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void winterGroupSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "wg.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void privateSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "wpSwim.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	public void testSchedule(View arg0) {
		//Pool schedule opener
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();
		String pdf = "test.pdf";
		InputStream in = null;
		OutputStream out = null;
		File file = new File(getFilesDir(), pdf);
		try {
			in = assetManager.open(pdf);
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/" + pdf),
				"application/pdf");

		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this,"Please download a PDF reader first", Toast.LENGTH_LONG).show();
			Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			i.setData(Uri.parse("https://play.google.com/store/search?q=pdf+reader&c=apps"));
			startActivity(i);
		}    

	}
	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}



}





