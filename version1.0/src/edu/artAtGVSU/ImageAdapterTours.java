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

public class ImageAdapterTours extends BaseAdapter {
    private Context mContext;
    ArrayList<Bitmap> images;
    int item;
    
	public ImageAdapterTours(Context c, ArrayList<Bitmap> bitmapList) {
		// TODO Auto-generated constructor stub
		mContext = c;
		//TypedArray attr = mContext.obtainStyledAttributes(R.styleable.TourGallery);
		//item = attr.getResourceId(R.styleable.TourGallery_android_galleryItemBackground, 1);
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
		imgView.setLayoutParams(new Gallery.LayoutParams(250, 250));
		imgView.setScaleType(ImageView.ScaleType.FIT_XY);
		imgView.setBackgroundResource(item);
		
		final RelativeLayout borderImg = new RelativeLayout(mContext);
		borderImg.setPadding(2, 2, 2, 2);
		borderImg.setBackgroundColor(Color.parseColor("#909090"));
		borderImg.addView(imgView);
		return borderImg;
	}
}