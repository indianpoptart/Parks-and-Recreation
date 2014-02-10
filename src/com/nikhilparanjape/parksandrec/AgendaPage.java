package com.nikhilparanjape.parksandrec;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AgendaPage extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9900")));
		bar.setDisplayShowHomeEnabled(true);
		bar.setTitle("Agenda");
		super.onCreate(savedInstanceState);

		
		if(CheckNetwork.isInternetAvailable(AgendaPage.this)) //returns true if internet available
		{
			setContentView(R.layout.agenda_menu);
			WebView mywebview = (WebView) findViewById(R.id.webView1);
			mywebview.loadUrl("http://ridgefieldparksandrec.org/agenda/");
			WebSettings webSettings = mywebview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			mywebview.getSettings().setRenderPriority(RenderPriority.HIGH);
			mywebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			Toast.makeText(AgendaPage.this,"Loading...",Toast.LENGTH_LONG).show();
		}   
		else
		{

			Toast.makeText(AgendaPage.this,"No Internet Connection",Toast.LENGTH_LONG).show();
		}  

	}
	public void halt(){

	}





}





