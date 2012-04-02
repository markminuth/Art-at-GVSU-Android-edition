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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class TourActivity extends Activity {
	int selectedPos;
	ArrayList<Bitmap> images = new ArrayList<Bitmap>();

	/*
	 * Get tour image from URL
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tour);
		final ArrayList<Tour> tours = ParseToursXML.getTours();
		for (int i = 0; i < tours.size(); i++) {
			images.add(fetchImage(tours.get(i).imageMainURL));
		}

		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapterTours(this, images));

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView parent, View view,
					int position, long id) {
				selectedPos = position;
				try {
					TextView text = (TextView) findViewById(R.id.tourNameText);
					text.setText(tours.get(selectedPos).tourName);
				} catch (Exception e) {

				}
			}

			public void onNothingSelected(AdapterView arg0) {
				// TODO Auto-generated method stub
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				if (position == selectedPos) {
					Intent intent = new Intent(view.getContext(),
							MapTourActivity.class);
					Tour t = tours.get(position);
					intent.putExtra("tourID", position);
					startActivityForResult(intent, 0);
				}
			}
		});
	}
}
