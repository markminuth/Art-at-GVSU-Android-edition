package edu.artAtGVSU;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArtWorkDetailsActivity extends Activity {

	ArtWork aOpened;
	String[] details;
	ListView detailList;
	Context c = this;
	ImageButton fav;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artdetails);
		aOpened = ArtWorkObjectSetUp.getArtWork();
		
		//Load if all information is there if not must call Parse Artwork
		if(!aOpened.artID.isEmpty() || !aOpened.artistID.isEmpty()){
			if(aOpened.historicalContext.length() >= 240){
				details = new String[]{"Physical description \n" + aOpened.description, 
					"Historical narrative \n" + aOpened.historicalContext.substring(0, 240) + "...",
					"Medium \n" + aOpened.medium,
					"Date \n" + aOpened.workDate,
					"Location \n" + aOpened.locName, 
					"More works by \n" + aOpened.artistName,
					"Identifier \n" + aOpened.idno};
			}else{
				details = new String[]{"Physical description \n" + aOpened.description, 
						"Historical narrative \n" + aOpened.historicalContext,
						"Medium \n" + aOpened.medium,
						"Date \n" + aOpened.workDate,
						"Location \n" + aOpened.locName, 
						"More works by \n" + aOpened.artistName,
						"Identifier \n" + aOpened.idno};
			}
			
			detailList = (ListView) findViewById(R.id.artDetailList);
			detailList.setAdapter(new ArrayAdapter<String>(this, R.layout.artdetail_list, details));
			detailList.setTextFilterEnabled(true);
			
			//Set up all detials of the artwork 
			ImageView i = (ImageView) findViewById(R.id.art_image);

			//i.setLayoutParams( new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
			i.setImageBitmap(fetchImage(aOpened.imageURLLarge));    

			
			fav = (ImageButton) findViewById(R.id.favorite);
			//if(searchForText( aOpened.artID + "~" + aOpened.artTitle + "~" + aOpened.description)){
				fav.setImageResource(R.drawable.favorite);
			//}
	
			TextView artTitle = (TextView) findViewById(R.id.artPieceTitle);
			artTitle.setText(aOpened.artTitle);
			TextView artTitleB = (TextView) findViewById(R.id.artPieceTitleBack);
			artTitleB.setText(aOpened.artTitle);
			
			TextView artistName = (TextView) findViewById(R.id.artistName);
			artistName.setText(aOpened.artistName);
			TextView artistNameB = (TextView) findViewById(R.id.artistNameBack);
			artistNameB.setText(aOpened.artistName);
		}else{
		
		}
		
		
		//Button listeners
		final ImageButton zoomButton = (ImageButton) findViewById(R.id.zoomButton);
		zoomButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ArtWorkZoomActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		
		final ImageButton favorite = (ImageButton) findViewById(R.id.favorite);
		favorite.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				fav.setImageResource(R.drawable.favorite);
				String favString = aOpened.artID + "~" + aOpened.artTitle + "~" + aOpened.description;
				//if(searchForText(favString)){
					writeToFile(favString);
				//}else{
					//deleteFromFile(favString);
				//}
			}
		});
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
	
	public void writeToFile(String fav) {

		String temp = new String(fav);

		try {
			temp += "\n" + readFromFile();
			FileOutputStream fOut = openFileOutput("FavoritesFile3.txt", MODE_WORLD_READABLE);

			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write(temp);

			osw.flush();
			osw.close();
		} catch (IOException e) {
		}
	}
	public String readFromFile() {

		String temp = "";

		final int lang = 999999;

		try {

			FileInputStream fIn = openFileInput("FavoritesFile3.txt");
			InputStreamReader isr = new InputStreamReader(fIn);

			char[] inputBuffer = new char[lang];
			isr.read(inputBuffer);
			String readString = new String(inputBuffer);
			temp += readString;

		} catch (IOException e) {
		}

		return temp;
	}
//	
//	public boolean searchForText(String fav)
//	{
//	String temp = new String(fav);
//	String finTemp = "";
//	
//		
//		finTemp += readFromFile();
//		
//		for (int i=0;i<finTemp.length()-temp.length();i++)
//		{
//			String test = finTemp.substring(i, i+(temp.length()));
//			if (fav.equalsIgnoreCase(test))
//			{
//				return false;
//			}
//		}return true;
//	}
//	
	public void deleteFromFile(String fav) {

		String temp = new String(fav);
		String finTemp = "";
			finTemp += readFromFile();
			
			for (int i=0;i<finTemp.length()-temp.length();i++)
			{
				//parse the string to find the one i want to delete
				String test = finTemp.substring(i, i+(temp.length()));
				if (fav.equalsIgnoreCase(test))
				{
					//remove the string
					String finTemp1=finTemp.substring(0, i);
					
					String finTemp2=finTemp.substring(i+fav.length(), finTemp.length());
					finTemp2.concat(finTemp1);
					finTemp=finTemp1+finTemp2;
					writeToFile(finTemp);
				}
				else 
				{
					//the file was not found
				}
			}

	}
	
	
}
