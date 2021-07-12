package com.tttg.user.collagephotoeditor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.tttg.user.collagephotoeditor.R;


public class FrameAdapter extends ArrayAdapter<Integer>{

	private Context context;
	private Integer[] hairImage;

	public FrameAdapter(Context context, int resource, Integer[] hairImage) {
		super(context, resource,hairImage);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.hairImage=hairImage;
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.frame_adapter, null);
			holder = new ViewHolder();
			holder.hairImageView =(ImageView) v.findViewById(R.id.hairImageView);


			v.setTag(holder);
			
		}
		else
				holder=(ViewHolder)v.getTag();
		
		holder.hairImageView.setBackgroundResource(hairImage[position]);
		
		return v;
	}

	private class ViewHolder {
		ImageView hairImageView;
		
	}
}