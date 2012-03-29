package edu.gvsu.cis.bardslej.artAtGVSU;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class TourPinpoints extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> pinPoints = new ArrayList<OverlayItem>();
	private Context c;
	
	public TourPinpoints(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
		// TODO Auto-generated constructor stub
	}
	
	public TourPinpoints(Drawable m, Context context) {
		// TODO Auto-generated constructor stub
		this(m);
		c = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return pinPoints.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinPoints.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = pinPoints.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(c);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
	public void createPinPoint(OverlayItem item){
		pinPoints.add(item);
		this.populate();
	}

}
