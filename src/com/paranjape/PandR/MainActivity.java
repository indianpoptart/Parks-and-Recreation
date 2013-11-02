package com.paranjape.PandR;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button button1;
	private void call() {
	    try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:2034380069"));
	        startActivity(callIntent);
	    } catch (ActivityNotFoundException e) {
	        Log.e("hello android dialing example", "Call failed", e);
	    }
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);   
		
		if(button1.isPressed()){
			try {
				call();
			} catch (ActivityNotFoundException activityException) {
				Log.e("Calling a Phone Number", "Call failed", activityException);
				Toast.makeText(this, "Call Failed!", Toast.LENGTH_LONG).show();
			}
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onClick(View v){
		v.getId();
		if(button1.isPressed()){
			try {

				call();
			} catch (ActivityNotFoundException activityException) {
				Log.e("Calling a Phone Number", "Call failed", activityException);
				Toast.makeText(this, "Call Failed!", Toast.LENGTH_LONG).show();
			}
		}
	}
}

