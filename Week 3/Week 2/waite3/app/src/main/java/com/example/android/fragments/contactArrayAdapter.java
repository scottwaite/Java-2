package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.contact;

import java.util.List;

public class contactArrayAdapter extends ArrayAdapter<contact> {

	private Context context;
	private List<contact> objects;
	
	public contactArrayAdapter(Context context, int resource, List<contact> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		contact contact = objects.get(position);
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.contact_listitem, null);
		
		ImageView image = (ImageView) view.findViewById(R.id.ivcontactImage);
		image.setImageResource(contact.getImageResource());
		
		TextView tv = (TextView) view.findViewById(R.id.tvcontactName);
		tv.setText(contact.getcontactName());
		
		return view;
	}

}
