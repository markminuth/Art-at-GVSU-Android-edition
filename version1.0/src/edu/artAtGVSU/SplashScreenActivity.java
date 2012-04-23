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
import android.view.Menu;
import android.view.MenuItem;
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
	private static final int MENU1 = Menu.FIRST;
	private static final int MENU2 = Menu.FIRST +1;
	private static final int MENU3 = Menu.FIRST +2;
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0,MENU1,0,"Quit");
	    menu.add(0,MENU2,0,"Delete All");
	    menu.add(0,MENU3,0,"Delete Selected");
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case MENU1:
	        	//Quit
	            finish();
	            return true;
	        case MENU2:
	        	//delete all
	        	//writingBlankFile();
	            return true;
	        case MENU3:
	        	//delete Selected!
	        	//deleteSelected();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
