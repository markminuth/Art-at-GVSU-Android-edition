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
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
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
	ArtDetailsItemsAdapter adapter;
	ImageButton fav;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artdetails);
		aOpened = ArtWorkObjectSetUp.getArtWork();
		
		// Add details to list in order to pass list to Adapter
		ArrayList<String> textInfo = new ArrayList<String>();
		textInfo.add(aOpened.description);
		textInfo.add(aOpened.artTitle + ": " + aOpened.historicalContext);
		textInfo.add(aOpened.medium);
		textInfo.add(aOpened.workDate);
		textInfo.add(aOpened.locName + " - " + aOpened.locNotes);
		textInfo.add(aOpened.artistName);
		textInfo.add(aOpened.idno);

		detailList = (ListView) findViewById(R.id.artDetailList);
		adapter = new ArtDetailsItemsAdapter(c, R.layout.artdetail_list,
				textInfo);
		detailList.setAdapter(adapter);

		// Set up artwork title and artist name
		TextView artTitle = (TextView) findViewById(R.id.artPieceTitle);
		artTitle.setText(aOpened.artTitle);
		TextView artTitleB = (TextView) findViewById(R.id.artPieceTitleBack);
		artTitleB.setText(aOpened.artTitle);

		TextView artistName = (TextView) findViewById(R.id.artistName);
		artistName.setText(aOpened.artistName);
		TextView artistNameB = (TextView) findViewById(R.id.artistNameBack);
		artistNameB.setText(aOpened.artistName);

		// Set up image of artwork
		ImageView i = (ImageView) findViewById(R.id.art_image);
		i.setImageBitmap(fetchImage(aOpened.imageURLLarge));

		// Set up favorite button
		fav = (ImageButton) findViewById(R.id.favorite);
		// if(searchForText( aOpened.artID)){
		// fav.setImageResource(R.drawable.favorite_selected);
		// }else{
		fav.setImageResource(R.drawable.favorite);
		// }

		// Button listeners
		final ImageButton shareButtonC = (ImageButton) findViewById(R.id.shareButton);
		shareButtonC.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				alertbox();
			}
		});
		
		//About Button Action
		final ImageButton aboutButton = (ImageButton) findViewById(R.id.aboutIcon);
		aboutButton.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					aboutButton.setImageResource(R.drawable.about_icon_selected);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					aboutButton.setImageResource(R.drawable.about_icon);
				}
				return false;
			}
		});
		
		aboutButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
					Intent intent = new Intent(v.getContext(), AboutActivity.class);
					startActivityForResult(intent, 0);
				}
		});
		
		// Search Button Action
		final ImageButton searchButton = (ImageButton) findViewById(R.id.searchIcon);
		searchButton.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					searchButton.setImageResource(R.drawable.search_selected);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					searchButton.setImageResource(R.drawable.search);
				}
				return false;
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SearchActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		final ImageButton zoomButton = (ImageButton) findViewById(R.id.zoomButton);
		zoomButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						ArtWorkZoomActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		final ImageButton favorite = (ImageButton) findViewById(R.id.favorite);
		favorite.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				fav.setImageResource(R.drawable.favorite_selected);

				// Thread favoriteButton = new Thread(){
				// public void run(){
				if (aOpened.iconImageURL.isEmpty()
						|| aOpened.iconImageURL == null) {
					aOpened.iconImageURL = " ";
				}
				if (aOpened.artID.isEmpty() || aOpened.artID == null) {
					aOpened.artID = " ";
				}
				if (aOpened.artTitle.isEmpty() || aOpened.artTitle == null) {
					aOpened.artTitle = " ";
				}
				if (aOpened.description.isEmpty()
						|| aOpened.description == null) {
					aOpened.description = " ";
				}
				String favString = "<B>" + aOpened.iconImageURL + "~"
						+ aOpened.artID + "~" + aOpened.artTitle + "~"
						+ aOpened.description;
				// alertbox("TEST", favString);
				// if(searchForText(favString)){
				writeToFile(favString);
				// }else{
				// deleteFromFile(favString);
				// }
				// }
				// };
			}
		});
	}
	
	protected void alertbox() {
		final CharSequence[] items = {"Open in Browser", "Facebook", "Twitter", "SMS"};
		new AlertDialog.Builder(this)
				.setItems(items, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						if(which == 0){
							Uri uriUrl = Uri.parse("http://gvsuartgallery.org/pawtucket/index.php/Detail/Object/Show/object_id/" + aOpened.artID);
							Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
							startActivity(launchBrowser);
						}
						if(which == 1){
							Toast.makeText(c, "Needs Implementing", Toast.LENGTH_LONG);
						}
						if(which == 2){
							Toast.makeText(c, "Needs Implementing", Toast.LENGTH_LONG);
						}
						if(which == 3){
							Toast.makeText(c, "Needs Implementing", Toast.LENGTH_LONG);
						}
					}
				})
				.setTitle("Share")
				.setCancelable(true)
				.setNeutralButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
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
		img = ((BitmapDrawable) getResources().getDrawable(R.drawable.app_icon))
				.getBitmap();
		return img;
	}

	public void writeToFile(String fav) {

		String temp = new String(fav);

		try {
			temp += readFromFile();
			FileOutputStream fOut = openFileOutput("favoriteArt1.txt",
					MODE_WORLD_READABLE);

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

			FileInputStream fIn = openFileInput("favoriteArt1.txt");
			InputStreamReader isr = new InputStreamReader(fIn);

			char[] inputBuffer = new char[lang];
			isr.read(inputBuffer);
			String readString = new String(inputBuffer);
			temp += readString;

		} catch (IOException e) {
		}

		return temp;
	}

	public boolean searchForText(String fav) {
		String temp = new String(fav);
		String finTemp = "";

		finTemp += readFromFile();

		for (int i = 0; i < finTemp.length() - temp.length(); i++) {
			String test = finTemp.substring(i, i + (temp.length()));
			if (fav.equalsIgnoreCase(test)) {
				return false;
			}
		}
		return true;
	}

	// public void deleteFromFile(String fav) {
	//
	// String temp = new String(fav);
	// String finTemp = "";
	// finTemp += readFromFile();
	//
	// for (int i=0;i<finTemp.length()-temp.length();i++)
	// {
	// //parse the string to find the one i want to delete
	// String test = finTemp.substring(i, i+(temp.length()));
	// if (fav.equalsIgnoreCase(test))
	// {
	// //remove the string
	// String finTemp1=finTemp.substring(0, i);
	//
	// String finTemp2=finTemp.substring(i+fav.length(), finTemp.length());
	// finTemp2.concat(finTemp1);
	// finTemp=finTemp1+finTemp2;
	// writeToFile(finTemp);
	// }
	// else
	// {
	// //the file was not found
	// }
	// }
	//
	// }
	//
	//

}
