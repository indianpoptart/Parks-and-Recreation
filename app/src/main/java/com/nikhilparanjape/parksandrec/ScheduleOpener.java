package com.nikhilparanjape.parksandrec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("WorldReadableFiles")
public class ScheduleOpener extends Activity {
	@SuppressWarnings("deprecation")
	public void main(String pdf){
		Toast.makeText(this,"Loading Schedule...", Toast.LENGTH_LONG).show();
		AssetManager assetManager = getAssets();

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
