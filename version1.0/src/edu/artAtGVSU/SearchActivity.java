package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class SearchActivity extends Activity{
	ArrayList<ArtWork> searchedArtWork;
	ListView list;
	Context c = this;
	ItemsAdapterWithImage adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.search);  
	    list = (ListView) findViewById(R.id.searchList);
	    final ImageButton searchB = (ImageButton) findViewById(R.id.searchIcon);
	    
	    searchB.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					searchB.setImageResource(R.drawable.search_selected);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					searchB.setImageResource(R.drawable.search);
				}
				return false;
			}
		});
		searchB.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				String[] loading = new String[]{"Loading..."};
				list.setAdapter(new ArrayAdapter<String>(c, R.layout.loading_list, loading));
				Thread getSearch = new Thread(){
					@Override
					public void run() {
						
						final EditText searchText = (EditText) findViewById(R.id.searchTextBox);
						String userText = searchText.getText().toString();
						if(!userText.isEmpty()){
							try{
								searchedArtWork = ParseArtWorkXML.artWorkRequestIdentifier(userText);
							}catch(Exception e){
								String[] noResults = new String[]{"No Results Found"};
								list.setAdapter(new ArrayAdapter<String>(c, R.layout.loading_list, noResults));
							}
						}
						Message msg = new Message();
						Bundle resBundle = new Bundle();
						resBundle.putString("status", "SUCCESS");
						msg.obj = resBundle;
						handlerSearch.sendMessage(msg);
					}
				};
				getSearch.start();
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(c, "WORKS", Toast.LENGTH_LONG);
				ArtWork seleted = searchedArtWork.get(pos);
				ArtWork a = ParseArtWorkXML.artWorkRequestID(seleted.artID);
				ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
				Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
				((Activity) c).startActivity(intent);
			}
		});
	}
	
	private Handler handlerSearch = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			adapter = new ItemsAdapterWithImage(c, R.layout.search_list, searchedArtWork);
			list.setAdapter(adapter);
		}
	};
}
