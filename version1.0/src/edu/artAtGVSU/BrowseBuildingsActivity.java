package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseBuildingsActivity extends Activity {
//	ArrayList<Bitmap> buildingArt;
	static ArrayList<Building> buildings = new ArrayList<Building>();
	ListView list;
	BrowseBuildingItemAdapter adapter;
	Context b = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		buildings = ParseBrowseXML.buildingNamesDataRequest(String.valueOf(getIntent().getIntExtra("campusID",1)));
		list = (ListView) findViewById(R.id.browseList);
		adapter = new BrowseBuildingItemAdapter(this, R.layout.browselist_item, buildings);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View view, int pos, long id) 
			{
				Toast.makeText(getApplicationContext(),"this works", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
