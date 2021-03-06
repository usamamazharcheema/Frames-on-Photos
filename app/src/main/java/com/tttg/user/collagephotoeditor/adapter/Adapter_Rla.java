package com.tttg.user.collagephotoeditor.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tttg.user.collagephotoeditor.R;

public class Adapter_Rla extends BaseAdapter {

	// Declare variables
	private Activity activity;
	private String[] filepath;
	private String[] filename;

	private static LayoutInflater inflater = null;

	public Adapter_Rla(Activity a, String[] fpath, String[] fname) {
		activity = a;
		filepath = fpath;
		filename = fname;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return filepath.length;

	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.gridview_item, null);

		
		// Locate the ImageView in gridview_item.xml
		ImageView image = (ImageView) vi.findViewById(R.id.image);

		
		

		// Decode the filepath with BitmapFactory followed by the position
		Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);

		// Set the decoded bitmap into ImageView
		image.setImageBitmap(bmp);
		return vi;
	}
}