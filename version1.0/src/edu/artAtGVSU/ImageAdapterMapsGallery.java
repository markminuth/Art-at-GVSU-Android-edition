package edu.artAtGVSU;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImageAdapterMapsGallery extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> images;
    int item;
    
	public ImageAdapterMapsGallery(Context c, ArrayList<Bitmap> bitmapList) {
		// TODO Auto-generated constructor stub
		mContext = c;
		//TypedArray attr = mContext.obtainStyledAttributes(R.styleable.TourGallery);
		//item = attr.getResourceId(R.styleable.TourGallery_android_galleryItemBackground, 0);
		//attr.recycle();
		this.images = bitmapList;
	}
	public int getCount() {
		return images.size();
	}
	public Object getItem(int position) {
		return images.get(position);
	}
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imgView = new ImageView(mContext);
		imgView.setImageBitmap(images.get(position));
		imgView.setLayoutParams(new Gallery.LayoutParams(125, 125));
		imgView.setScaleType(ImageView.ScaleType.FIT_XY);
		imgView.setBackgroundResource(item);
		
		final RelativeLayout borderImg = new RelativeLayout(mContext);
		borderImg.setPadding(2, 2, 2, 2);
		borderImg.setBackgroundColor(Color.BLACK);
		borderImg.addView(imgView);
		return borderImg;
	}
}