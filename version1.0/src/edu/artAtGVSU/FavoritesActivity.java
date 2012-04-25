                                           
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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
			
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) 
			{
				String selectedString = favArtWorkArrayList.get(pos);
	        	//deleteFromFile(selectedString);
//				ArtWork a = ParseArtWorkXML.artWorkRequestID(tokenTwo(selectedString,1));
//				ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
//				Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
//				((Activity) c).startActivity(intent);
				showPopUp2(pos);
				
			}
		});
	
		favList.setOnLongClickListener(new OnLongClickListener() {
			
			
			public void OnLongClickListener(AdapterView<?> arg0, View arg1, int pos,long arg3) 
			{
				showPopUp2(pos);
								
			}

			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				return false;
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

			FileInputStream fIn = openFileInput("favoriteArtFile8.txt");
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
			FileOutputStream fOut = openFileOutput("favoriteArtFile8.txt", MODE_WORLD_READABLE);

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
	
	
	
	
	private void showPopUp2(int pos2) {
		
			final int pos3=pos2;
		 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		 helpBuilder.setTitle("Pop Up");
		 helpBuilder.setMessage("This is a Simple Pop Up");
		 helpBuilder.setPositiveButton("Details",
		   new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int which) {
		    	
				String selectedString = favArtWorkArrayList.get(pos3);
	        	//deleteFromFile(selectedString);
				ArtWork a = ParseArtWorkXML.artWorkRequestID(tokenTwo(selectedString,1));
				ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
				Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
				((Activity) c).startActivity(intent);
		    }
		   });

		 helpBuilder.setNegativeButton("Remove", new DialogInterface.OnClickListener() {

		  public void onClick(DialogInterface dialog, int which) {
			  	String selectedString = favArtWorkArrayList.get(pos3);
	        	deleteFromFile(selectedString);
		  }
		 });
		 
		 helpBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {

		  public void onClick(DialogInterface dialog, int which) {
		   // Do nothing
			  
		  }
		 });

		 // Remember, create doesn't show the dialog
		 AlertDialog helpDialog = helpBuilder.create();
		 helpDialog.show();

		}
	
}