package com.nikhilparanjape.parksandrec;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class AgendaPage extends AppCompatActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(CheckNetwork.isInternetAvailable(AgendaPage.this)) //returns true if internet available
		{
			setContentView(R.layout.agenda_menu);
			final ActionBar actionBar = getSupportActionBar();

			actionBar.setHomeAsUpIndicator(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_arrow_back).color(Color.WHITE).sizeDp(IconicsDrawable.ANDROID_ACTIONBAR_ICON_SIZE_DP).paddingDp(IconicsDrawable.ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP));
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle("Agenda");

			WebView mywebview = (WebView) findViewById(R.id.webView1);
			mywebview.loadUrl("http://ridgefieldparksandrec.org/agenda/");
			String url = new String("http://ridgefieldparksandrec.org/agenda");
			WebSettings webSettings = mywebview.getSettings();
			webSettings.setJavaScriptEnabled(true);
			mywebview.getSettings().setRenderPriority(RenderPriority.HIGH);
			mywebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
			mywebview.setWebViewClient(new WebViewClient(){
				public void onPageFinished(WebView mywebview, String url){
					findViewById(R.id.loadingpanel).setVisibility(View.GONE);
				}
			});
		}   
		else{
			Toast.makeText(AgendaPage.this,"No Internet Connection",Toast.LENGTH_LONG).show();
		}
	}
}





