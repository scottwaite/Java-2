package com.scottwaite.android.fragments.data;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/

import com.scottwaite.android.fragments.R;

import java.util.ArrayList;
import java.util.List;

public class contactData {

	private List<contact> contacts = new ArrayList<contact>();
	public List<contact> getcontacts() {
		return contacts;
	}

	public contactData() {
		contacts.add(new contact("Ouija", R.drawable.ouija, 10.95,
				"Rating: 8%"));
		contacts.add(new contact("Nightcrawler", R.drawable.nightcrawler, 10.95,
				"Rating: 94%"));
		contacts.add(new contact("Fury", R.drawable.fury, 10.95,
				"Rating: 78%"));
	}


}
