
package edu.artAtGVSU;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BrowseItemAdapter extends ArrayAdapter<Bitmap>{
	int resource;
    String response;
    Context context;
    //Initialize adapter
    public BrowseItemAdapter(Context context, int resource, List<Bitmap> items) {
        super(context, resource, items);
        this.resource=resource;
 
    }


	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout alertView;
        //Get the current alert object
       Bitmap image = getItem(position);
 
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
        ImageView artIcon = (ImageView)alertView.findViewById(R.id.buildingIcon);
        TextView nameText = (TextView)alertView.findViewById(R.id.buildingName);
 
        
        //Assign the appropriate data from our alert object above
        if(position == 0){
        	nameText.setText("Allendale Campus");
        }else if(position == 1)
        	nameText.setText("Meijer Campus in Holland");
        else if(position == 2)
        	nameText.setText("Muskegon Campus");
        else if(position == 3)
        	nameText.setText("PEW Campus (Grand Rapids)");
        else if(position == 4)
        	nameText.setText("Traverse City Campus");
        else
        	nameText.setText("We lost the records on this site.");
        
        artIcon.setImageBitmap(image);

        return alertView;
    }

}
