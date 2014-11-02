package com.scottwaite.android.fragmentdemo;

/*
Name: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: October 30th 2014
 */

import android.app.Activity;
import android.os.Bundle;

import com.scottwaite.android.fragmentdemo.fragment.ButtonsFragment;
import com.scottwaite.android.fragmentdemo.fragment.ButtonsFragment.OnButtonClickListener;
import com.scottwaite.android.fragmentdemo.fragment.DisplayFragment;

public class MainActivity extends Activity implements OnButtonClickListener {

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(_savedInstanceState == null) {
			ButtonsFragment frag = ButtonsFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.container1, frag, ButtonsFragment.TAG).commit();
		}
	}

	@Override
	public void displayText(String _text) {
		DisplayFragment frag = (DisplayFragment)getFragmentManager().findFragmentByTag(DisplayFragment.TAG);
		
		if(frag == null) {
			frag = DisplayFragment.newInstance(_text);
			getFragmentManager().beginTransaction().replace(R.id.container2, frag, DisplayFragment.TAG).commit();
		} else {
			frag.setDisplayText(_text);
		}
	}
}
