package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class TourActivity extends Activity {
	ArrayList<Bitmap> images = new ArrayList<Bitmap>();
	static ArrayList<Tour> tours = new ArrayList<Tour>();
	static int selectedPos;
	private static final int MENU1 = Menu.FIRST;
	private static final int MENU2 = Menu.FIRST +1;
	private static final int MENU3 = Menu.FIRST +2;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tour);
		Thread tourGallery = new Thread(){
			@Override
			public void run() {
				tours = ParseToursXML.getTours();
				for (int i = 0; i < tours.size(); i++) {
					images.add(fetchImage(tours.get(i).imageMainURL));
				}
				Message msg = new Message();
				Bundle resBundle = new Bundle();
				resBundle.putString("status", "SUCCESS");
				msg.obj = resBundle;
				handlerGallery.sendMessage(msg);
			}
		};
		tourGallery.start();
	}
	  
	
	/*
	 * Gets the image from the URL sent to the method and returns a Bitmap of the image
	 */
	public Bitmap fetchImage(String tImageURL) {
		URL url;
		try {
			url = new URL(tImageURL);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			Bitmap img;
			img = BitmapFactory.decodeStream(is);
			return img;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 
	 */
	private Handler handlerGallery = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Gallery gallery = (Gallery) findViewById(R.id.gallery);
			gallery.setAdapter(new ImageAdapterTours(getBaseContext(), images));
			
			//set to middle of the tours
			gallery.setSelection(1);
			
			gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView parent, View view, int position, long id) {
					selectedPos = position;
					TextView text = (TextView) findViewById(R.id.tourNameText);
					text.setText(tours.get(selectedPos).tourName);
				}
	
				public void onNothingSelected(AdapterView arg0) {
					// TODO Auto-generated method stub
				}
			});
	
			gallery.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView parent, View view, int position, long id) {
					if (position == selectedPos) {
						Intent intent = new Intent(view.getContext(), MapTourActivity.class);
						Tour t = tours.get(position);
						intent.putExtra("tourID", position);
						startActivityForResult(intent, 0);
					}
				}
			});
		}
	};
	


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
