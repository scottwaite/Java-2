package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.Contact;

import java.util.List;

public class ContactArrayAdapter extends ArrayAdapter<Contact> {

	private Context context;
	private List<Contact> objects;
	
	public ContactArrayAdapter(Context context, int resource, List<Contact> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Contact contact = objects.get(position);
		
		LayoutInflater inflater = 
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.contact_listitem, null);
		
		ImageView image = (ImageView) view.findViewById(R.id.ivContactImage);
		image.setImageResource(contact.getImageResource());
		
		TextView tv = (TextView) view.findViewById(R.id.tvContactName);
		tv.setText(contact.getContactName());
		
		return view;
	}

}
