package edu.artAtGVSU;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// thread for displaying the SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					ArrayList<Tour> tours = new ArrayList<Tour>();
					tours = ParseToursXML.toursRequest();
					for (int i = 0; i < tours.size(); i++){
						//having trouble with geoPoint
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
