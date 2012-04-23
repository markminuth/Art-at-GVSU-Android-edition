package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseBuildingsActivity extends Activity {

	ArrayList<Bitmap> buildingArt;
	static ArrayList<Building> buildings = new ArrayList<Building>();
	ListView list;
	BrowseItemAdapter adapter;
	Context b = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		buildings = ParseBrowseXML.buildingNamesDataRequest(String.valueOf(getIntent().getIntExtra("campusID",1)));
		list = (ListView) findViewById(R.id.browseList);
		buildingArt = new ArrayList<Bitmap>();
		for(int i=0;i<buildings.size();++i){
			int resID = getResources().getIdentifier("loc_" + buildings.get(i).getBuildingID(), "drawable", "edu.artAtGVSU");
			Bitmap building = BitmapFactory.decodeResource(getResources(), resID);
			buildingArt.add(building);
		}
		adapter = new BrowseItemAdapter(this, R.layout.browselist_item, buildingArt);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View view, int pos, long id) 
			{
				Toast.makeText(getApplicationContext(),"this works", Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(c, BrowseBuildingsActivity.class);
//				Campus camp = campuses.get(pos);
//				intent.putExtra("campusID", Integer.parseInt(camp.getCampusID()));
//				startActivityForResult(intent, 0);
			}
		});
	}
}
