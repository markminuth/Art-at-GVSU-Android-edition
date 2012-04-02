package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArtWorkDetailsActivity extends ListActivity {

	ArtWork aOpened;
	String[] details;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.artdetails);
		aOpened = ArtWorkObjectSetUp.getArtWork();
		if(aOpened.historicalContext.length() >= 200){
			details = new String[]{"Physical description \n" + aOpened.description, 
				"Historical narrative \n" + aOpened.historicalContext.substring(0, 200) + "...",
				"Medium \n" + aOpened.medium,
				"Date \n",
				"Location \n" + aOpened.locName, 
				"More works by \n" + aOpened.artistName,
				"Identifier \n" + aOpened.idno};
		}else{
			details = new String[]{"Physical description \n" + aOpened.description, 
					"Historical narrative \n" + aOpened.historicalContext,
					"Medium \n" + aOpened.medium,
					"Date \n",
					"Location \n" + aOpened.locName, 
					"More works by \n" + aOpened.artistName,
					"Identifier \n" + aOpened.idno};
		}
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.artdetail_list, details));
		
		//ListView listView = getListView();
		//listView.setTextFilterEnabled(true);
		
		//Set up all detials of the artwork 
		//ImageView i = (ImageView) findViewById(R.id.artImage);
		//i.setImageBitmap(fetchImage(aOpened.imageURL));
		//i.setMinimumHeight(200);
		//i.setMinimumWidth(300);

		//TextView artTitle = (TextView) findViewById(R.id.artPieceTitle);
		//artTitle.setText(aOpened.artTitle);
		
		//TextView artistName = (TextView) findViewById(R.id.artistName);
		//artistName.setText(aOpened.artistName);
		
		
		
		//TextView textView = new TextView(this);
		//textView.setText(aOpened.artTitle + " " + aOpened.artistName);
		//setContentView(textView);
	}
	
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
		img = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.app_icon)).getBitmap();
		return img;
	}
}
