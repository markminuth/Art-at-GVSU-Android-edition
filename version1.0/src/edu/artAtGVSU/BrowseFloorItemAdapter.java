package edu.artAtGVSU;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BrowseFloorItemAdapter extends ArrayAdapter<Floor>{

	int resource;
	String response;
	Context context;
	ArrayList<Bitmap> buildingArt;
	
	public BrowseFloorItemAdapter(Context context, int textViewResourceId, List<Floor> items) {
		super(context, textViewResourceId, items);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		LinearLayout alertView = null;
		Floor flo = getItem(pos);
		//Now inflate the view
		if(convertView==null){
			alertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater layInflate;
			layInflate = (LayoutInflater)getContext().getSystemService(inflater);
			layInflate.inflate(resource, alertView, true);
		}else{
			alertView = (LinearLayout) convertView;
		}
		buildingArt = new ArrayList<Bitmap>();
		int resID = parent.getResources().getIdentifier("loc_" + flo.floorID, "drawabl", "edu.artAtGVSU");
		Bitmap floor = BitmapFactory.decodeResource(parent.getResources(), resID);
		
		//Get text boxes from search_list.xml file
		ImageView artIcon = (ImageView)alertView.findViewById(R.id.floorIcon);
		TextView nameText = (TextView)alertView.findViewById(R.id.floorName);
		
		//Assign correct data from the alert object
		nameText.setText(flo.floorName);
		//Assign correct image
		artIcon.setImageBitmap(floor);
		
		return alertView;
	}
}
