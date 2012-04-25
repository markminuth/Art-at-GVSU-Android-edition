package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseActivity extends Activity {
	ArrayList<Campus> campuses = new ArrayList<Campus>();
	ListView list;
	BrowseItemAdapter adapter;
	Context c = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		
		if(ParseBrowseXML.campuses.isEmpty()){
			Thread browseDataUpdate = new Thread(){
				@Override
				public void run() {
					campuses = ParseBrowseXML.campusNamesDataRequest();
					Message msg = new Message();
					Bundle resBundle = new Bundle();
					resBundle.putString("status", "SUCCESS");
					msg.obj = resBundle;
					handlerDataUpdate.sendMessage(msg);
				}
			};
			browseDataUpdate.start();
		}
		
		
		//testing art call by building or floor
		//ArrayList<ArtWork> a = ParseBrowseXML.artworkNamesDataRequest("10");
		
		list = (ListView) findViewById(R.id.browseList);
		Campus loadingDefault = new Campus("Loading...", "0");
		campuses.add(loadingDefault);
		adapter = new BrowseItemAdapter(this, R.layout.browselist_item, campuses);
		list.setAdapter(adapter);
	}
	
	private Handler handlerDataUpdate = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			adapter = new BrowseItemAdapter(c, R.layout.browselist_item, campuses);
			list.setAdapter(adapter);

			list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView parent, View view, int pos, long id) 
				{	
					Intent intent = new Intent(c, BrowseBuildingsActivity.class);
					Campus camp = campuses.get(pos);
					intent.putExtra("campusID", Integer.parseInt(camp.getCampusID()));
					startActivityForResult(intent, 0);
				}
			});
		}
	};
}