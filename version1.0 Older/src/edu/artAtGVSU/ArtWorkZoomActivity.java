package edu.artAtGVSU;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ArtWorkZoomActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.art_zoom);
		
		WebView webImage = (WebView) findViewById(R.id.webViewForImage);
		webImage.loadUrl(ArtWorkObjectSetUp.getArtWork().imageURLMedium);
		webImage.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
	}
}
