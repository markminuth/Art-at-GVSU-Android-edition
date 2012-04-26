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
import android.widget.Toast;

public class ArtWorkZoomActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		ZoomImageViewer zoom = new ZoomImageViewer(this);
//		ArtWork aOpened = ArtWorkObjectSetUp.getArtWork();
//		zoom.setBackgroudImage(aOpened.getImage());
//		setContentView(zoom);
//		Toast.makeText(this, aOpened.imageURLLarge, Toast.LENGTH_LONG);
		setContentView(R.layout.art_zoom);
	}
}
