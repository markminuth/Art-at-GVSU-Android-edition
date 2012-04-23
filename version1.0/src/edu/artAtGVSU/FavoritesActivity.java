                                           
package edu.artAtGVSU;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoritesActivity extends Activity {
	private static final int MENU1 = Menu.FIRST;
	private static final int MENU2 = Menu.FIRST +1;
	private static final int MENU3 = Menu.FIRST +2;
	
	TextView t;	
	ListView favList;
	FavItemsAdapter favAdapter;
	Context c= this;
	ArrayList<String> favArtWorkArrayList = new ArrayList<String>();
	
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.favorites);
		
		List<String> temp = tokenStr(readFromFile());
		
		favArtWorkArrayList = new ArrayList<String>();
		
		if(temp.size() <= 1){
			favArtWorkArrayList.add(" ~ ~No favs were found... ~ ");
		}else{
			for(int i =1; i< temp.size();i++){
				favArtWorkArrayList.add(temp.get(i));
			}
		}
		
		favList=(ListView)findViewById(R.id.listViewFav);
		favAdapter = new FavItemsAdapter(c, R.layout.favorites_list, favArtWorkArrayList);
		favList.setAdapter(favAdapter);	
		favList.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemLongClick(AdapterView<?> arg0, View arg1, int pos,long arg3) 
			{
			String selectedString = favArtWorkArrayList.get(pos);
        	deleteFromFile(selectedString);
			//ArtWork a = ParseArtWorkXML.artWorkRequestID(tokenTwo(selectedString,1));
			//ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
			//Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
			//((Activity) c).startActivity(intent);
			
			}
	
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) 
			{
				String selectedString = favArtWorkArrayList.get(pos);
	        	//deleteFromFile(selectedString);
				ArtWork a = ParseArtWorkXML.artWorkRequestID(tokenTwo(selectedString,1));
				ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
				Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
				((Activity) c).startActivity(intent);
				
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
	        	writingBlankFile();
	            return true;
	        case MENU3:
	        	//delete Selected!
	        	deleteSelected();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	public List<String> tokenStr(String fav){
		
		String full = fav;
		String delims = "<B>";
		String[] tok = full.split(delims);
		List<String> tokens = Arrays.asList(tok);
		
		return tokens;
		
	}
	
	public String tokenTwo(String temp,int i)
	{
		String[] tokensTwo = temp.split("~");
		String fin = tokensTwo[i];
		return fin;
	}
	
	public void writingToFile(String fav) {

		String temp = new String(fav);

		try {
			FileOutputStream fOut = openFileOutput("favoriteArtFile4.txt", MODE_WORLD_READABLE);

			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write(temp);

			osw.flush();
			osw.close();
		} catch (IOException e) {
		}

	}

	public String readFromFile() {

		String temp = "";

		final int lang = 10000;

		try {

			FileInputStream fIn = openFileInput("favoriteArtFile4.txt");
			InputStreamReader isr = new InputStreamReader(fIn);

			char[] inputBuffer = new char[lang];
			isr.read(inputBuffer);
			String readString = new String(inputBuffer);
			temp += readString;

		} catch (IOException e) {
		}

		return temp;
	}

	
	
	public void deleteFromFile(String fav) {
		// Remove from array
		String temp = readFromFile();
		for( int i = 0; i < favArtWorkArrayList.size(); i++){
			String line = favArtWorkArrayList.get(i);
			if(line.contains(fav)){
				favArtWorkArrayList.remove(i);
				temp = temp.replace("<B>" + fav, "");
			}
		}	
		
		writingToFile(temp);
	}
	
	public void writingBlankFile() {

		try {
			FileOutputStream fOut = openFileOutput("favoriteArtFile4.txt", MODE_WORLD_READABLE);

			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			osw.write("");

			osw.flush();
			osw.close();
		} catch (IOException e) {
		}

	}
	
	public void deleteSelected() {
		
		SparseBooleanArray test =favList.getCheckedItemPositions();
		//Log.i(TAG,"checkedPositions: " + test.size());
		if (test != null)
		{
		    int count = favAdapter.getCount();
		    for ( int i=0;i<count;i++)
		    {
		        if (test.get(i))
					deleteFromFile(favArtWorkArrayList.get(i)); 	
		    }
		}
	}
}