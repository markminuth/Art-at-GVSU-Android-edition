package edu.artAtGVSU;

import android.graphics.Bitmap;

public class BitmapSaver {

		static Bitmap b;
		
		public BitmapSaver(Bitmap image){
			b = image;
		}
		
		public static Bitmap getBitmap(){
			return b;
		}
}
