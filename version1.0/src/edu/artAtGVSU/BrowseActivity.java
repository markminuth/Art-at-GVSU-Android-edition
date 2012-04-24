package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseActivity extends Activity {
	static ArrayList<Campus> campuses = new ArrayList<Campus>();
	ListView list;
	BrowseItemAdapter adapter;
	Context c = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		
		if(ParseBrowseXML.campuses.isEmpty()){
			campuses = ParseBrowseXML.campusNamesDataRequest();
		}
		
		//testing art call by building or floor
		//ArrayList<ArtWork> a = ParseBrowseXML.artworkNamesDataRequest("10");
		
		list = (ListView) findViewById(R.id.browseList);

		adapter = new BrowseItemAdapter(this, R.layout.browselist_item, campuses);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View view, int pos, long id) 
			{
//				Toast.makeText(getApplicationContext(),"this works", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(c, BrowseBuildingsActivity.class);
				Campus camp = campuses.get(pos);
				intent.putExtra("campusID", Integer.parseInt(camp.getCampusID()));
				startActivityForResult(intent, 0);
			}
		});
	}
}