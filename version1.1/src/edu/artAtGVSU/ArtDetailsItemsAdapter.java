package edu.artAtGVSU;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ArtDetailsItemsAdapter extends ArrayAdapter<String> {
	int resource;
	String response;
	Context context;
	
	//Initialize adapter
	public ArtDetailsItemsAdapter(Context context, int resource, List<String> items){
		super(context, resource, items);
		this.resource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LinearLayout alertView;
		String artDetail = getItem(position);
		
		if(convertView == null){
			alertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, alertView, true);
		}else{
			alertView = (LinearLayout) convertView;	
		}
		
		//Get strings from the artdetails.xml file
		TextView header = (TextView) alertView.findViewById(R.id.header);
		TextView details = (TextView) alertView.findViewById(R.id.details);
		
		//Assign data to the alert object
		if(position == 0){
			header.setText("Physical description");
		}if(position == 1){
			header.setText("Historical narrative");
		}if(position == 2){
			header.setText("Medium");
		}if(position == 3){
			header.setText("Date");
		}if(position == 4){
			header.setText("Location");
		}if(position == 5){
			header.setText("More works by");
		}if(position == 6){
			header.setText("Identifier");
		}
		//cut back content in order to fit more
		if(artDetail.length() > 80 && position == 1){
			details.setText(artDetail.substring(0, 80) + "...");
		}else{
			details.setText(artDetail);
		}
		
		
		return alertView;
	}
}
