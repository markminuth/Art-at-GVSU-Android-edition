package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MapTourActivity extends MapActivity {

	static MapView map;
	static MapController controller;
	static ArrayList<Bitmap> images;
	static List<Overlay> mapOverlays;
	static TourPinpoints itemizedoverlay;
	static Tour t;
	static Gallery gallery;
	static int selectedTour;
	int selectedPos;
	static Context c;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		c = this;
		map = (MapView) findViewById(R.id.mapView);
		map.displayZoomControls(true);
		map.setBuiltInZoomControls(true);
		
		mapOverlays = map.getOverlays();
		Drawable drawable = map.getResources().getDrawable(R.drawable.pin);
		itemizedoverlay = new TourPinpoints(drawable, map);
		
		//Create Thread to load tour points on map
		final Thread pinPoints = new Thread() {
			@Override
			public void run() {
				selectedTour = getIntent().getIntExtra("tourID", -1) + 1;
				t = ParseToursXML.getTour(String.valueOf(selectedTour));
				for (int i = 0; i < t.artPieces.size(); i++) {
					GeoPoint gp = t.artPieces.get(i).geoLoc;
					OverlayItem overlayItem;
					if(t.artPieces.get(i).artistName != null){
						//when information about the art is known
						overlayItem = new OverlayItem(gp, t.artPieces.get(i).artTitle, t.artPieces.get(i).artistName + "%" + t.artPieces.get(i).iconImageURL);
					}else{
						//no artist information known yet
						overlayItem = new OverlayItem(gp, t.artPieces.get(i).artTitle, "%");
					}
					itemizedoverlay.createPinPoint(overlayItem);
				}
				Message msg = new Message();
				Bundle resBundle = new Bundle();
				resBundle.putString("status", "SUCCESS");
				msg.obj = resBundle;
				handlerPinPoints.sendMessage(msg);
			}
		};
		pinPoints.start();
		
		controller = map.getController();
		
		//Create Thread to Parse icons of art pieces and fetch url images
		Thread galleryLoad = new Thread(){
			
			@Override
			public void run() {
				images = new ArrayList<Bitmap>();
				ParseArtWorkXML.setTour(t);
				
				// Get Icons for artwork in tour to display in gallery
				for (int i = 0; i < t.artPieces.size(); i++) {
					// if the artwork icon has not yet been set to tours it must make the web service call
					if (ParseArtWorkXML.getTour().artPieces.get(i).iconImageURL == null) {
						ParseArtWorkXML.artIconRequest(t.artPieces.get(i).artID, i);
						images.add(fetchImage(t.artPieces.get(i).getIconURL()));
					} else {
						images.add(fetchImage(ParseArtWorkXML.getTour().artPieces.get(i).getIconURL()));
					}
				}
				Message msg = new Message();
				Bundle resBundle = new Bundle();
				resBundle.putString("status", "SUCCESS");
				msg.obj = resBundle;
				handlerGallery.sendMessage(msg);
			}
		};
		galleryLoad.start();
		
		//Search Button Action
		final ImageButton searchButton = (ImageButton) findViewById(R.id.searchIcon);
		searchButton.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					searchButton.setImageResource(R.drawable.search_selected);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
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
		
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * Update UI with the pinpoints in the tour
	 */
	private Handler handlerPinPoints = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			mapOverlays.add(itemizedoverlay);
			// Focus Screen to correct location based on the tour
			if (selectedTour == 1) {
				controller.animateTo(t.artPieces.get(1).geoLoc);
				controller.setZoom(17);
			} else if (selectedTour == 2) {
				controller.animateTo(t.artPieces.get(18).geoLoc);
				controller.setZoom(17);
			} else {
				controller.animateTo(t.artPieces.get(1).geoLoc);
				controller.setZoom(15);
			}
			
			gallery = (Gallery) findViewById(R.id.gallery);	
			ArrayList<Bitmap> temp = new ArrayList<Bitmap>();
			for (int i = 0; i < t.artPieces.size(); i++) {
				//Bitmap image = (Bitmap) findViewById(R.drawable.app_icon);
				temp.add(image);
			}
			gallery.setAdapter(new ImageAdapterMapsGallery(c, temp));
			gallery.setSelection(2);
		}
	};
	
	/*
	 * Update UI with the gallery of images above map
	 */
	private Handler handlerGallery = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			gallery = (Gallery) findViewById(R.id.gallery);
			gallery.setAdapter(new ImageAdapterMapsGallery(c, images));
			gallery.setSelection(2);
			
			gallery.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView parent, View view,
						int position, long id) {
					gallery.refreshDrawableState();
					RelativeLayout borderImg = (RelativeLayout)view;
					selectedPos = position;
					itemizedoverlay.onTap(selectedPos);
					view.setFocusable(true);	
					//borderImg.setBackgroundColor(Color.RED);
				}
			});
		}
	};
		
}
