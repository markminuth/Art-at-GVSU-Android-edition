package edu.artAtGVSU;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

public class BrowseBuildingItemAdapter extends ArrayAdapter<Building> {
	int resource;
    String response;
    Context context;
	ArrayList<Bitmap> buildingArt;
    //Initialize adapter
    public BrowseBuildingItemAdapter(Context context, int resource, List<Building> items) {
        super(context, resource, items);
        this.resource=resource;
 
    }


	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout alertView;
        //Get the current alert object
       Building build = getItem(position);
 
        //Inflate the view
        if(convertView==null)
        {
            alertView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, alertView, true); 
            
        }
        else
        {
        	alertView = (LinearLayout) convertView;
        }
        
		buildingArt = new ArrayList<Bitmap>();
		int resID = parent.getResources().getIdentifier("loc_" + build.buildingID, "drawable", "edu.artAtGVSU");
		Bitmap building = building = BitmapFactory.decodeResource(parent.getResources(), resID);
			
        //Get the text boxes from the search_list.xml file
        ImageView artIcon = (ImageView)alertView.findViewById(R.id.buildingIcon);
        TextView nameText = (TextView)alertView.findViewById(R.id.buildingName);
 
        
        //Assign the appropriate data from our alert object above
        nameText.setText(build.buildingName);
        
        
       if(building == null){
        	building = BitmapFactory.decodeResource(parent.getResources(), R.drawable.building_default);

        }
       
       if(build.buildingName == "Loading..."){
       		building = null;
       }
       
        artIcon.setImageBitmap(building);

        return alertView;
    }
}
