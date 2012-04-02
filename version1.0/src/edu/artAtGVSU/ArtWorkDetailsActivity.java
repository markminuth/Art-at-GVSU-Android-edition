package edu.artAtGVSU;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArtWorkDetailsActivity extends Activity {

	ArtWork aOpened;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.artdetails);
		aOpened = ArtWorkObjectSetUp.getArtWork();
		TextView textView = new TextView(this);
		textView.setText(aOpened.artTitle + " " + aOpened.artistName);
		setContentView(textView);
	}
}
