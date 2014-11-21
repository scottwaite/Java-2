package com.scottwaite.navigationdrawerexample;

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

public class HelpFragment extends Fragment {

	public HelpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_help, container, false);

		return rootView;
	}

}
