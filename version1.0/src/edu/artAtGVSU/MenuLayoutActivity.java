package edu.artAtGVSU;

import java.util.ArrayList;

import android.R.array;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MenuLayoutActivity extends TabActivity {
	
	private static final int MENU1 = Menu.FIRST;
	private static final int MENU2 = Menu.FIRST +1;
	private static final int MENU3 = Menu.FIRST +2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Set up menu tab items
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Create an Intent to launch an Activity for the tab (to be reused)
		//intent = new Intent().setClass(this, TourTabGroupActivity.class);
		intent = new Intent(this, TourActivity.class);
		spec = tabHost.newTabSpec("tours").setIndicator("TOURS", res.getDrawable(R.drawable.tab_tours)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, BrowseActivity.class);
		spec = tabHost.newTabSpec("browse").setIndicator("BROWSE", res.getDrawable(R.drawable.tab_browse)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, FavoritesActivity.class);
		spec = tabHost.newTabSpec("favorites").setIndicator("FAVORITES",res.getDrawable(R.drawable.tab_favorites)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
		
		//Tab visual aspects
		for(int i=0;i < tabHost.getTabWidget().getChildCount();i++) 
		{ 
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setTextSize(11);
    		tabHost.getTabWidget().getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams((width / 3), 80));
    		tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#CDCDCD"));
    		//tabHost.getTabWidget().getChildAt(i).setPadding(0, 0, -1, 0);
		} 
		
		
		//App Icon Button Action
		final ImageButton backAppButton = (ImageButton) findViewById(R.id.appHeaderIcon);
		backAppButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0) {
				finish();
			}
			
		});
		
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
		
		//Search Button Action
		final ImageButton searchButton = (ImageButton) findViewById(R.id.searchIcon);
		searchButton.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					searchButton.setImageResource(R.drawable.search_selected);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
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
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0,MENU1,0,"Quit");
	    menu.add(0,MENU2,0,"Delete All");
	    menu.add(0,MENU3,0,"Delete Selected");
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case MENU1:
	        	//Quit
	            finish();
	            return true;
	        case MENU2:
	        	//delete all
	        	//writingBlankFile();
	            return true;
	        case MENU3:
	        	//delete Selected!
	        	//deleteSelected();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}