package edu.artAtGVSU;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesActivity extends Activity {
	TextView t;	
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		t = new TextView(this);
		setContentView(t);

		//writingToFile("Test 1");
		//deleteFromFile("WORK");

		String temp = "";
		temp = readFromFile();

		t.setTextColor(Color.BLACK);
		t.setText(temp);
	}
	
	public void writingToFile(String fav) {

		String temp = new String(fav);

		try {
			temp += "\n" + readFromFile();
			FileOutputStream fOut = openFileOutput("FavoritesFile3.txt", MODE_WORLD_READABLE);

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

			FileInputStream fIn = openFileInput("FavoritesFile3.txt");
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