package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class TourPinpoints extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> pinPoints = new ArrayList<OverlayItem>();
	private Context c;
	
	public TourPinpoints(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		// TODO Auto-generated constructor stub
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
	protected boolean onBalloonTap(int index) {
	Toast.makeText(c, "onBalloonTap for overlay index " + index, Toast.LENGTH_LONG).show();
	  //OverlayItem item = pinPoints.get(index);
	  //AlertDialog.Builder dialog = new AlertDialog.Builder(c);
	  //dialog.setTitle(item.getTitle());
	  //dialog.setMessage(item.getSnippet());
	  //dialog.show();
	  return true;
	}
	
	public void createPinPoint(OverlayItem item){
		pinPoints.add(item);
		this.populate();
	}

}
