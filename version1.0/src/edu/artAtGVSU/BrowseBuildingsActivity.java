package edu.artAtGVSU;

import java.util.ArrayList;

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
import android.view.View;
import android.widget.AdapterView;
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
		setContentView(R.layout.browse);
		if(ParseBrowseXML.buildings.isEmpty()){
			Thread browseDataUpdate = new Thread(){
				@Override
				public void run() {
					buildings = ParseBrowseXML.buildingNamesDataRequest(String.valueOf(getIntent().getIntExtra("campusID",1)));
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
//					Intent intent = new Intent(c, BrowseFloorActivity.class);
					Building build = buildings.get(pos);
//					intent.putExtra("buildingID", Integer.parseInt(build.getBuildingID()));
//					startActivityForResult(intent, 0);
				}
			});
		}
	};
}
