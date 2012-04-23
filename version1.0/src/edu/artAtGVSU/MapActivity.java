package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MapActivity extends Activity {

	MapView map;
	MapController controller;
	ArrayList<Bitmap> images;
	int selectedPos;
	
	private static final int MENU1 = Menu.FIRST;
	private static final int MENU2 = Menu.FIRST +1;
	private static final int MENU3 = Menu.FIRST +2;
	
	/*
	 * Get tour image from URL
	 */
	public Bitmap fetchImage(String tImageURL) {
		URL url;
		Bitmap img;
		try {
			url = new URL(tImageURL);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			img = BitmapFactory.decodeStream(is);
			return img;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// returns this icon if URL doesn't retrieve artwork
		img = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher))
				.getBitmap();
		return img;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		map = (MapView) findViewById(R.id.mapView);
		map.displayZoomControls(true);
		map.setBuiltInZoomControls(true);
		
		int selectedTour = getIntent().getIntExtra("tourID", -1) + 1;
		final Tour t = ParseToursXML.getTour(String.valueOf(selectedTour));
		
	}
	
	
	
	
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
