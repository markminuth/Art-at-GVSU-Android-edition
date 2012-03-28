package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TourGroup extends ActivityGroup {

	public static TourGroup group;
	private ArrayList history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.history = new ArrayList();
		group = this;

		// Start the root activity withing the group and get its view
		View view = getLocalActivityManager().startActivity("TourActivity", new Intent(this, TourActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();

		// Replace the view of this ActivityGroup
		replaceView(view);
	}

	public void replaceView(View v) {
		// Adds the old one to history
		history.add(v);
		// Changes this Groups View to the new View.
		setContentView(v);
	}

	public void back() {
		if (history.size() > 0) {
			history.remove(history.size() - 1);
			setContentView((Integer) history.get(history.size() - 1));
		} else {
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		TourGroup.group.back();
		return;
	}
}
