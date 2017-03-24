package com.example.coffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	Context context;//
	String[] names;
	String [] descriptions;
	LayoutInflater inflater;

	public MyAdapter(Context con, String[] myNames, String[] myDescriptions) {

		this.context = con;
		this.names = myNames;
		this.descriptions = myDescriptions;
		inflater = (LayoutInflater.from(con));

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = new ViewHolder(); 
		
		convertView = inflater.inflate(R.layout.single_item, null);

		holder.first = (TextView) convertView.findViewById(R.id.tvFirst);
		holder.second = (TextView) convertView.findViewById(R.id.tvSecond);

		holder.first.setText(names[position]);
		holder.second.setText(descriptions[position]);

		return convertView;
	}
	
	public static class ViewHolder{
		
		TextView first;
		TextView second;
		
	}

}
