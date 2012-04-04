
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

public class ItemsAdapter extends ArrayAdapter<ArtWork>{
	int resource;
    String response;
    Context context;
    //Initialize adapter
    public ItemsAdapter(Context context, int resource, List<ArtWork> items) {
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
        //Get the text boxes from the listitem.xml file
        ImageView artIcon =(ImageView)alertView.findViewById(R.id.searchIconImage);
        TextView nameText =(TextView)alertView.findViewById(R.id.artNameText);
        TextView descripText =(TextView)alertView.findViewById(R.id.descripText);
 
        //Assign the appropriate data from our alert object above
        nameText.setText(aw.artTitle);
        descripText.setText(aw.description);
        //artIcon.setImageBitmap(aw.imageURL);
        
        return alertView;
    }
}
