package edu.artAtGVSU;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		setContentView(R.layout.favorites);

		//writingToFile("http://t0.gstatic.com/images?q=tbn:ANd9GcR0QV4FPsaUcN1hW6oleMCgVqdEgu0zN2C9XS99DHc05bFMw6h5NATpEJK8JQ~255~Art Title~DESCRIPTION");
		//deleteFromFile("WORK");
		
		
		List<String> temp = tokenStr(readFromFile());
		
		//TextView t = new TextView(c);
		//setContentView(t);
		//t.setText(readFromFile());
		
		ArrayList<String> favArtWorkArrayList = new ArrayList<String>();
		
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
		
		//favArtWorkArrayList.add(aOpened.description)
//		String[] favArtWork;
////		ListView list;
//		favArtWork = tokenStr(readFromFile());
		
	}
	
	public List<String> tokenStr(String fav){
		
		String full = fav;
		String delims = "<B>";
		String[] tok = full.split(delims);
		List<String> tokens = Arrays.asList(tok);
		
		return tokens;
		
	}
	
//	public String tokenTwo(String temp,int i)
//	{
//		String[] tokensTwo = temp.split("~");
//		String fin = tokensTwo[i];
//		return fin;
//	}
	
	
	public void writingToFile(String fav) {

		String temp = new String(fav);

		try {
			temp += readFromFile();
			FileOutputStream fOut = openFileOutput("aFavFile18.txt", MODE_WORLD_READABLE);

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

			FileInputStream fIn = openFileInput("aFavFile18.txt");
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