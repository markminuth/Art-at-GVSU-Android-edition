package edu.artAtGVSU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class BrowseArtWorkActivity extends Activity{

	static ArrayList<ArtWork> artwork = new ArrayList<ArtWork>();
	ListView list;
	BrowseArtWorkItemAdapter adapter;
	Context c = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_buildings);

		Thread browseDataUpdate = new Thread() {
			@Override
			public void run() {
				artwork = ParseBrowseXML.artworkNamesDataRequest(String.valueOf(getIntent().getIntExtra("floorID", 1)));
				Collections.sort(artwork, new Comparator() {

					public int compare(Object o1, Object o2) {
						ArtWork a1 = (ArtWork) o1;
						ArtWork a2 = (ArtWork) o2;
						return a1.getArtTitle().compareToIgnoreCase(a2.getArtTitle());
					}

				});
				Message msg = new Message();
				Bundle resBundle = new Bundle();
				resBundle.putString("status", "SUCCESS");
				msg.obj = resBundle;
				handlerDataUpdate.sendMessage(msg);
			}
		};
		browseDataUpdate.start();
		
		list = (ListView) findViewById(R.id.browseList);
		ArtWork loadingDefault = new ArtWork();
		loadingDefault.setArtTitle("Loading...");
		artwork = new ArrayList<ArtWork>();
		artwork.add(loadingDefault);
		adapter = new BrowseArtWorkItemAdapter(c, R.layout.browselist_item, artwork);
		list.setAdapter(adapter);
		
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
	}
	
	
	private Handler handlerDataUpdate = new Handler(){
		@Override
		public void handleMessage(Message msg) {

			adapter = new BrowseArtWorkItemAdapter(c, R.layout.browselist_item, artwork);
			list.setAdapter(adapter);

			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView parent, View view, int pos, long id) 
				{	
					ArtWork selected = artwork.get(pos);
					ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(selected);
					Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
					((Activity) c).startActivity(intent);
				}
			});
		}
	};
}
