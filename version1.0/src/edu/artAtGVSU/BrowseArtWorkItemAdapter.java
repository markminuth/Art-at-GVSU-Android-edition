package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

public class BrowseArtWorkItemAdapter extends ArrayAdapter<ArtWork>{

	int resource;
	String response;
	Context context;
	
	public BrowseArtWorkItemAdapter(Context context, int resource, List<ArtWork> items) {
		super(context, resource, items);
		this.resource = resource;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int pos, View convertView, ViewGroup parent){
		LinearLayout alertView = null;
		ArtWork art = getItem(pos);
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
		//Assign image
		//ArtWork a = ParseArtWorkXML.artIconRequest(art.artID, -1);
		Bitmap b = //fetchImage(a.iconImageURL);
		
	    //if(b == null){
	    	BitmapFactory.decodeResource(parent.getResources(), R.drawable.app_icon);
	    //}
	    
	    if(art.artTitle == "Loading..."){
	    	b = null;
	    }
	    
		nameText.setText(art.artTitle);
		
		//artIcon.setImageBitmap(b);
		
		return alertView;
	}
	
	/*
	 * Gets the image from the URL sent to the method and returns a Bitmap of the image
	 */
	public Bitmap fetchImage(String tImageURL) {
		URL url;
		try {
			url = new URL(tImageURL);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			Bitmap img;
			img = BitmapFactory.decodeStream(is);
			return img;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
