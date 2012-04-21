package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BrowseActivity extends Activity {
	ArrayList<Bitmap> buildingArt;
	ListView list;
	BrowseImageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse);
		list = (ListView) findViewById(R.id.browseList);

		buildingArt = new ArrayList<Bitmap>();
		for (int i = 2; i < 7; ++i) {
			int resID = getResources().getIdentifier("loc_" + i, "drawable", "edu.artAtGVSU");
			Bitmap building = BitmapFactory.decodeResource(getResources(), resID);
			buildingArt.add(building);
		}

		adapter = new BrowseImageAdapter(this, R.layout.browselist_item, buildingArt);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) 
			{
				Toast.makeText(getApplicationContext(), "This worked", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
//				Toast.makeText(c, "WORKS", Toast.LENGTH_LONG);
//				ArtWork seleted = searchedArtWork.get(pos);
//				ArtWork a = ParseArtWorkXML.artWorkRequestID(seleted.artID);
//				ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
//				Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
//				((Activity) c).startActivity(intent);
			}
		});
	}
}