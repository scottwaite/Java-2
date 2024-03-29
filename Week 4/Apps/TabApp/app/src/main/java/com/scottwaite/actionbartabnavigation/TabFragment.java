package com.scottwaite.actionbartab;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Navigating Around
Date: November 20, 2014
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabFragment extends Fragment {

	private int index;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle data = getArguments();
		index = data.getInt("idx");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment, null);
		TextView tv = (TextView) v.findViewById(R.id.msg);
		tv.setText("Forecast " + (index + 1));

		return v;

	}



}
