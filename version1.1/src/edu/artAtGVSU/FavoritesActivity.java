package edu.artAtGVSU;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.w3c.dom.Text;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesActivity extends Activity {
	
	TextView t;	
	ListView favList;
	FavItemsAdapter favAdapter;
	Context c= this;
	
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//setContentView(R.layout.favorites);

		//writingToFile("Test 1");
		//deleteFromFile("WORK");

		String[] temp2 = tokenStr(readFromFile());	
		TextView t = new TextView(c);
		setContentView(t);
		t.setText(temp2[1]);
		
		//ArrayList<String> favArtWorkArrayList = new ArrayList<String>();
		//favArtWorkArrayList.add("test");
		//favArtWorkArrayList.add("test2");
		
		//for(int i =0; i< temp2.length;i++){
		//	favArtWorkArrayList.add(temp2[i]);
		//}
		
//		favList=(ListView)findViewById(R.id.listViewFav);
//		favAdapter = new FavItemsAdapter(c, R.layout.favorites_list, favArtWorkArrayList);
//		favList.setAdapter(favAdapter);
		//favArtWorkArrayList.add(aOpened.description)
//		String[] favArtWork;
////		ListView list;
//		favArtWork = tokenStr(readFromFile());
		
	}
	
	public String[] tokenStr(String fav){
		
		String full = fav;
		String delims = "<B>";
		String[] tokens = full.split(delims);
		
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
			temp += "\n" + readFromFile();
			FileOutputStream fOut = openFileOutput("artFavorites3.txt", MODE_WORLD_READABLE);

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

			FileInputStream fIn = openFileInput("artFavorites3.txt");
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

		String temp = new String(fav);
		String finTemp = "";
			finTemp += readFromFile();
			
			for (int i=0;i<finTemp.length()-temp.length();i++)
			{
				//parse the string to find the one i want to delete
				String test = finTemp.substring(i, i+(temp.length()));
				if (fav.equalsIgnoreCase(test))
				{
					//remove the string
					String finTemp1=finTemp.substring(0, i);
					
					String finTemp2=finTemp.substring(i+fav.length(), finTemp.length());
					finTemp2.concat(finTemp1);
					finTemp=finTemp1+finTemp2;
					writingToFile(finTemp);
				}
				else 
				{
					//the file was not found
				}
			}

	}
}