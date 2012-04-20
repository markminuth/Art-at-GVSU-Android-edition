package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ArtWorkZoomActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ZoomImageViewer zoom = new ZoomImageViewer(this);
		ArtWork aOpened = ArtWorkObjectSetUp.getArtWork();
		zoom.setBackgroudImage(fetchImage(aOpened.imageURLLarge));
		setContentView(zoom);
		//setContentView(R.layout.art_zoom);
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
}
