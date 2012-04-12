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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MenuLayoutActivity extends TabActivity {

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
		final View aboutView = findViewById(R.id.aboutScreen);
		aboutView.setVisibility(View.GONE);
		
		//Tab visual aspects
		for(int i=0;i < tabHost.getTabWidget().getChildCount();i++) 
		{ 
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setTextSize(11);
    		tabHost.getTabWidget().getChildAt(i).setLayoutParams(new LinearLayout.LayoutParams((width / 3), 50));
    		tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#CDCDCD"));
    		//tabHost.getTabWidget().getChildAt(i).setPadding(0, 0, -1, 0);
		} 
		
		
		
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
				aboutView.setVisibility(View.VISIBLE);
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
				aboutView.setVisibility(View.GONE);
				Intent intent = new Intent(v.getContext(), SearchActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	        if(keyCode == KeyEvent.KEYCODE_SEARCH){
				Intent intent = new Intent(this, SearchActivity.class);
				startActivityForResult(intent, 0);
	        }
			return true;
	} 
}