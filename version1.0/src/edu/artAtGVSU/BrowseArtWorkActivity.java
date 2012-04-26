package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

public class BrowseArtWorkActivity extends Activity {

	//static ArrayList<Floor> floors = new ArrayList<Floor>();
	//ListView list;
	//BrowseFloorItemAdapter adapter;
	//Context flo = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_buildings);
		
		if(ParseBrowseXML.floors.isEmpty()){
			//floors = ParseBrowseXML.floorNamesDataRequest(String.valueOf(getIntent().getIntExtra("buildingID", 1)));
		}
		
//		list = (ListView) findViewById(R.id.browseList);
//		adapter = new BrowseFloorItemAdapter(this, R.layout.browselist_item, floors);
//		list.setAdapter(adapter);
//		list.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				Toast.makeText(getApplicationContext(), "this works",
//						Toast.LENGTH_SHORT).show();
//				
//			}
//		});
	}
}
