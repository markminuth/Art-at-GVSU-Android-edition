package edu.artAtGVSU;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
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
		
		int index = getIntent().getIntExtra("index", 0);
		final Thread artDetailCall = new Thread(){
			public void run() {
				aOpened = ArtWorkObjectSetUp.getArtWork();
				aOpened = ParseArtWorkXML.artWorkRequestID(aOpened.artID);
				Message msg = new Message();
				Bundle resBundle = new Bundle();
				resBundle.putString("status", "SUCCESS");
				msg.obj = resBundle;
				handlerDetailsUpdate.sendMessage(msg);
			}
		};
		artDetailCall.start();
		
		//Initial empty list in array adapter shown until info is added
		ArrayList<String> t = new ArrayList<String>();
		for(int i = 0; i < 7; i++){
			t.add("");
		}
		
		detailList = (ListView) findViewById(R.id.artDetailList);
		adapter = new ArtDetailsItemsAdapter(c, R.layout.artdetail_list, t);
		detailList.setAdapter(adapter);


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
				//Intent shareIntent = new Intent();
				Bitmap imageToSend = fetchImage(aOpened.imageURLMedium);
				Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("image/jpg");
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				imageToSend.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
				File f = new File("sdcard" + File.separator + "Pictures" + File.separator + "artImage.jpg");
				try {
				    f.createNewFile();
				    FileOutputStream fo = new FileOutputStream(f);
				    fo.write(bytes.toByteArray());
				} catch (IOException e) {                       
				        e.printStackTrace();
				}
			
				share.putExtra(Intent.EXTRA_TEXT, aOpened.imageURLMedium);
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/Pictures/artImage.jpg"));
				startActivity(Intent.createChooser(share, "Share"));
				//Save to SD card
//				String mFilePath = "";
//		        try {
//		        	String mBaseFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";
//		        	mFilePath = mBaseFolderPath + "artImage.jpg";	        
//		        	FileOutputStream stream = new FileOutputStream(mFilePath);
//		        	imageToSend.compress(CompressFormat.JPEG, 100, stream);
//					stream.flush();
//					stream.close();
//				}catch(Exception e){
//					
//				}
		        
//				Uri artImage = Uri.parse("file:///sdcard/Pictures/picture.jpg");
//				shareIntent.setAction(Intent.ACTION_SEND);
//				shareIntent.putExtra(Intent.EXTRA_STREAM, artImage);
//				shareIntent.setType("image/jpeg");
//				startActivity(Intent.createChooser(shareIntent, "Share"));
//				//alertbox();
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
		

		final ImageButton mapViewButton = (ImageButton) findViewById(R.id.mapViewButton);
		mapViewButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {		
				Intent intent = new Intent(v.getContext(), MapTourActivity.class);
				intent.putExtra("notTour", -2);
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
			FileOutputStream fOut = openFileOutput("favoriteArtFile4.txt",
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

			FileInputStream fIn = openFileInput("favoriteArtFile4.txt");
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
	
	private Handler handlerDetailsUpdate = new Handler(){
		@Override
		public void handleMessage(Message msg) {
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
			adapter = new ArtDetailsItemsAdapter(c, R.layout.artdetail_list,textInfo);
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
			Bitmap b = fetchImage(aOpened.imageURLLarge);
			i.setImageBitmap(b);
			aOpened.setImage(b);
			
		}
	};
}
