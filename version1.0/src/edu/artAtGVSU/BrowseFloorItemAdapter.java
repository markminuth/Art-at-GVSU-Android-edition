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
	
	public BrowseFloorItemAdapter(Context context, int resource, List<Floor> items) {
		super(context, resource, items);
		this.resource = resource;
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
		
		//Get text boxes
		ImageView artIcon = (ImageView)alertView.findViewById(R.id.buildingIcon);
		TextView nameText = (TextView)alertView.findViewById(R.id.buildingName);
		
		//Assign correct data from the alert object
		nameText.setText(flo.floorName);
		//Assign default image
		Bitmap b = BitmapFactory.decodeResource(parent.getResources(), R.drawable.building_default);
		
	    if(flo.floorName == "Loading..."){
	    	b = null;
	    }
		
		artIcon.setImageBitmap(b);
		
		return alertView;
	}
}
