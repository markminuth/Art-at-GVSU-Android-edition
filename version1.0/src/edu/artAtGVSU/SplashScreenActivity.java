package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreenActivity extends Activity {
	ArrayList<Tour> tours = new ArrayList<Tour>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		// thread for displaying the SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					tours = ParseToursXML.toursRequest();
					for (int i = 0; i < tours.size(); i++) {
						ParseToursXML.toursIndividualDataRequest(tours.get(i).tourID);
					}
				} finally {
					finish();
					startActivity(new Intent("edu.artAtGVSU.MenuLayoutActivity"));
					stop();
				}
			}
		};
		splashTread.start();
	}

}
