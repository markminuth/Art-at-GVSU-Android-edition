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
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class FavoritesActivity extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(this);
		setContentView(textView);
		//deleteFromFile("Hey 4Ho!");
		//writingToFile("Hey 4Ho!");
		writingToFile("NEW LINE TRY 2");
		String temp = "";//readFromFile();
		temp = readFromFile();
		//this part does not work!
		//temp +=generateNoteOnSD("favsFile","hello world");

		//temp = deleteFromFile("He");
		textView.setText(temp);
	}
	
	
	//This part does not work!
	public String generateNoteOnSD(String sFileName, String sBody){
	    String answer ="1";
		try
	    {
	        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
	        if (!root.exists()) {
	        	answer +="2";
	            root.mkdirs();
	            answer +="3";
	        }
	        File gpxfile = new File(root, sFileName);
	        answer +="4";
	        FileWriter writer = new FileWriter(gpxfile);
	        answer +="5";
	        writer.append(sBody);
	        writer.flush();
	        writer.close();
	        answer +="6";
	        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	        answer +="7";
	    }
	    catch(IOException e)
	    {
	    	answer +="8";
	         e.printStackTrace();
	         
	    }
	    return answer;
	   }    	
	
public void writingToFile(String fav){
	
	String temp = new String(fav);
	
	try { 
		
		temp += "\n"+readFromFile();
		//FileWriter f = new FileWriter("/sdcard/download/impossible.txt");
        FileOutputStream fOut = openFileOutput("ArtFavorites2.txt",MODE_WORLD_READABLE);
   
        OutputStreamWriter osw = new OutputStreamWriter(fOut); 
        osw.write(temp);
        
        osw.flush();
        osw.close();
	}catch (IOException e) 
	{
	e.printStackTrace();
	
	AlertDialog.Builder delmessagebuilder = new AlertDialog.Builder(this);
	
    delmessagebuilder.setCancelable(false);
	
    delmessagebuilder.setMessage("File Access Error");
	
    delmessagebuilder.setNeutralButton("Okay", new DialogInterface.OnClickListener() 
    {
         public void onClick(DialogInterface dialog, int id) 
         {
        	 	dialog.dismiss();
         }
     });
	
	 delmessagebuilder.create().show();
}
	
}


public String readFromFile(){
	
	String temp="";
	
	final int lang =150;
	
	try { 

    FileInputStream fIn = openFileInput("ArtFavorites2.txt");
    InputStreamReader isr = new InputStreamReader(fIn);

    char[] inputBuffer = new char[lang];
    isr.read(inputBuffer);
    String readString = new String(inputBuffer);
    temp+=readString;
    
	}catch (IOException e) 
	{
	e.printStackTrace();
	
	AlertDialog.Builder delmessagebuilder = new AlertDialog.Builder(this);
	
    delmessagebuilder.setCancelable(false);
	
    delmessagebuilder.setMessage("File Access Error");
	
    delmessagebuilder.setNeutralButton("Okay", new DialogInterface.OnClickListener() 
    {
         public void onClick(DialogInterface dialog, int id) 
         {
        	 	dialog.dismiss();
         }
     	});
	
	 delmessagebuilder.create().show();
		}
    
    
    
	return temp;
	}



//	public String deleteFromFile(String fav){
//		
//	
//		String temp = new String(fav);
//		String finTemp = "";
//		
//		try { 
//			
//			finTemp += readFromFile();
//			
//			for (int i=0;i<finTemp.length()-temp.length();i++)
//			{
//				//parse the string to find the one i want to delete
//				String test = finTemp.substring(i, i+(temp.length()));
//				if (fav.equalsIgnoreCase(test))
//				{
//					finTemp="found somthing that matches :"+test;
////					//remove the string
////					String finTemp1=finTemp.substring(0, i);
////					String finTemp2=finTemp.substring(i+fav.length(), finTemp.length());
////					finTemp2.concat(finTemp1);
////					finTemp=finTemp2+"found hey ho4";
//					return finTemp;
//				}
//				else 
//				{
//					finTemp="found nothing that matches :"+test;
//					return finTemp;
//				}
//			}
//			
//			FileOutputStream fOut = openFileOutput("ArtFavorites.txt",MODE_WORLD_READABLE);
//	   
//	        OutputStreamWriter osw = new OutputStreamWriter(fOut); 
//	        osw.write(finTemp);
//	        
//	        osw.flush();
//	        osw.close();
//		}catch (IOException e) 
//		{
//		e.printStackTrace();
//		
//		AlertDialog.Builder delmessagebuilder = new AlertDialog.Builder(this);
//		
//	    delmessagebuilder.setCancelable(false);
//		
//	    delmessagebuilder.setMessage("File Access Error");
//		
//	    delmessagebuilder.setNeutralButton("Okay", new DialogInterface.OnClickListener() 
//	    {
//	         public void onClick(DialogInterface dialog, int id) 
//	         {
//	        	 	dialog.dismiss();
//	         }
//	     });
//		
//		 delmessagebuilder.create().show();
//		
//		
//
//	}return "end of file";
//	}
}
