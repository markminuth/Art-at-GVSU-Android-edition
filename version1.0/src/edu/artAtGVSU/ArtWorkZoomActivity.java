package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class ArtWorkZoomActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ZoomImageViewer zoom = new ZoomImageViewer(this);
		ArtWork aOpened = ArtWorkObjectSetUp.getArtWork();
		zoom.setBackgroudImage(BitmapSaver.getBitmap());
		setContentView(zoom);
//		Toast.makeText(this, aOpened.imageURLLarge, Toast.LENGTH_LONG);
//		setContentView(R.layout.art_zoom);
//		ImageView i = (ImageView) findViewById(R.id.zoomImage);
//		Bitmap b = fetchImage(ArtWorkObjectSetUp.getArtWork().imageURLMedium);
//		i.setBackgroundResource(R.drawable.about_icon_selected);
	}
	

	/*
	 * Get tour image from URL
	 */
	public Bitmap fetchImage(String tImageURL) {
		URL url;
		Bitmap img;
		try {
			url = new URL(tImageURL);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			img = BitmapFactory.decodeStream(is);
			return img;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// returns this icon if URL doesn't retrieve artwork
		img = ((BitmapDrawable) getResources().getDrawable(R.drawable.app_icon))
				.getBitmap();
		return img;
	}

}
