
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
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemsAdapterWithImage extends ArrayAdapter<ArtWork>{
	int resource;
    String response;
    Context context;
    //Initialize adapter
    public ItemsAdapterWithImage(Context context, int resource, List<ArtWork> items) {
        super(context, resource, items);
        this.resource=resource;
 
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout alertView;
        //Get the current alert object
        ArtWork aw = getItem(position);
 
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
        //Get the text boxes from the search_list.xml file
        ImageView artIcon =(ImageView)alertView.findViewById(R.id.searchIconImage);
        TextView nameText =(TextView)alertView.findViewById(R.id.artNameText);
        TextView descripText =(TextView)alertView.findViewById(R.id.descripText);
 
        //Assign the appropriate data from our alert object above
        if(aw.artTitle.length() > 35){
        	nameText.setText(aw.artTitle.substring(0, 35) + "...");
        }else{
        	nameText.setText(aw.artTitle);
        }
        if(aw.description.length() > 100){
        	descripText.setText(aw.description.substring(0, 100) + "...");
        }else{
        	descripText.setText(aw.description);
        }
        
        Bitmap image = fetchImage(aw.iconImageURL);
        if(image == null){
        	image = BitmapFactory.decodeResource(parent.getResources(), R.drawable.app_icon);
        }
        
        artIcon.setImageBitmap(image);
        
        return alertView;
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
