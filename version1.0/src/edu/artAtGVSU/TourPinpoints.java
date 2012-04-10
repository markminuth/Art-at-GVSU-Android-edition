package edu.artAtGVSU;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
		c = mapView.getContext();
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
		//ArtWork a = ParseArtWorkXML.getTour().artPieces.get(getBalloonIndexClicked(index));
		//if(a.artistName != null){
			//a = ParseArtWorkXML.getTour().artPieces.get(getBalloonIndexClicked(index));
			//ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(a);
		//}else{
			//keep check that the information for the art piece has been loaded
			//while(a.artistName == null){
				ArtWork a = ParseArtWorkXML.getTour().artPieces.get(getBalloonIndexClicked(index));
				
			//}
			ArtWorkObjectSetUp art = new ArtWorkObjectSetUp(ParseArtWorkXML.artWorkRequestID(a.artID, index));
		//}
		Intent intent = new Intent(c, ArtWorkDetailsActivity.class);
		((Activity) c).startActivity(intent);
		return true;
	}
	
	public int getBalloonIndexClicked(int index){
		return index;
	}
	
	public void createPinPoint(OverlayItem item){
		pinPoints.add(item);
		this.populate();
	}

}
