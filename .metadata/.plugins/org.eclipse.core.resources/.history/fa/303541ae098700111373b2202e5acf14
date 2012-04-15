package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FavItemsAdapter extends ArrayAdapter<String> {
	int resource;
	String response;
	Context context;
	 
	//Initialize adapter
	public FavItemsAdapter(Context context, int resource, List<String> items){
		super(context, resource, items);
		this.resource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		LinearLayout alertView;
		String wholeString = getItem(position);
		
		if(convertView == null){
			alertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, alertView, true);
		}else{
			alertView = (LinearLayout) convertView;	
		}
	
		ImageView favIcon = (ImageView)alertView.findViewById(R.id.favIconImage);
		TextView nameText =(TextView)alertView.findViewById(R.id.favTitle);
        TextView descripText =(TextView)alertView.findViewById(R.id.favDescripText);
        
        Bitmap iconURL = fetchImage(tokenTwo(wholeString, 0));
        String name = tokenTwo(wholeString,2);
        String descrip = tokenTwo(wholeString,3);
        
        favIcon.setImageBitmap(iconURL);
        nameText.setText(name);
        descripText.setText(descrip);
        
		return alertView;
	}
	
	public String tokenTwo(String temp,int i)
	{
		String[] tokensTwo = temp.split("~");
		String fin = tokensTwo[i];
		return fin;
	}
	
	 /*
		 * Get tour image from URL
		 */
		public Bitmap fetchImage(String tImageURL) {
			URL url;
			Bitmap img = null;
			try {
				url = new URL(tImageURL);

				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream is = connection.getInputStream();
				img = BitmapFactory.decodeStream(is);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return img;
		}
	
}
