package edu.artAtGVSU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseBuildingsActivity extends Activity {
	ArrayList<Bitmap> buildingArt;
	static ArrayList<Building> buildings = new ArrayList<Building>();
	ListView list;
	BrowseBuildingItemAdapter adapter;
	Context c = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_buildings);
		if(ParseBrowseXML.buildings.isEmpty()){
			Thread browseDataUpdate = new Thread(){
				@Override
				public void run() {
					buildings = ParseBrowseXML.buildingNamesDataRequest(String.valueOf(getIntent().getIntExtra("campusID",1)));
					Collections.sort(buildings, new Comparator(){
						 
			            public int compare(Object o1, Object o2) {
			                Building b1 = (Building) o1;
			                Building b2 = (Building) o2;
			               return b1.getBuildingName().compareToIgnoreCase(b2.getBuildingName());
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
		}

		list = (ListView) findViewById(R.id.browseList);
		Building loadingDefault = new Building("Loading...", "0");
		buildings = new ArrayList<Building>();
		buildings.add(loadingDefault);
		adapter = new BrowseBuildingItemAdapter(c, R.layout.browselist_item, buildings);
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

			adapter = new BrowseBuildingItemAdapter(c, R.layout.browselist_item, buildings);
			list.setAdapter(adapter);

			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView parent, View view, int pos, long id) 
				{	
					// figure out whether it should go to floors or artwork
					Building build = buildings.get(pos);
					ArrayList<Floor> floorCheck = ParseBrowseXML.floorNamesDataRequest(build.buildingID);
					if(floorCheck.size() >= 1){
						Intent intent = new Intent(c, BrowseFloorActivity.class);				
						intent.putExtra("buildingID", Integer.parseInt(build.getBuildingID()));
						startActivityForResult(intent, 0);
					}else{
						Intent intent = new Intent(c, BrowseArtWorkActivity.class);				
						intent.putExtra("floorID", Integer.parseInt(build.getBuildingID()));
						startActivityForResult(intent, 0);
					}
				}
			});
		}
	};
}
