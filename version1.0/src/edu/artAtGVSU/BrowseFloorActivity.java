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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseFloorActivity extends Activity{

	static ArrayList<Floor> floors = new ArrayList<Floor>();
	ListView list;
	BrowseFloorItemAdapter adapter;
	Context c = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_buildings);
		
		if (ParseBrowseXML.floors.isEmpty()) {
			Thread browseDataUpdate = new Thread() {
				@Override
				public void run() {
					floors = ParseBrowseXML.floorNamesDataRequest(String
							.valueOf(getIntent().getIntExtra("buildingID", 1)));
					Collections.sort(floors, new Comparator() {

						public int compare(Object o1, Object o2) {
							Floor f1 = (Floor) o1;
							Floor f2 = (Floor) o2;
							return f1.getFloorName().compareToIgnoreCase(f2.getFloorName());
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
		Floor loadingDefault = new Floor("0", "Loading...");
		floors = new ArrayList<Floor>();
		floors.add(loadingDefault);
		adapter = new BrowseFloorItemAdapter(c, R.layout.browselist_item, floors);
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

			adapter = new BrowseFloorItemAdapter(c, R.layout.browselist_item, floors);
			list.setAdapter(adapter);

			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView parent, View view, int pos, long id) 
				{	
					Floor f = floors.get(pos);
					Intent intent = new Intent(c, BrowseArtWorkActivity.class);				
					intent.putExtra("floorID", Integer.parseInt(f.floorID));
					startActivityForResult(intent, 0);
				}
			});
		}
	};
}
