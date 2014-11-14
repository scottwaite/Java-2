package com.scottwaite.android.fragments.data;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import com.scottwaite.android.fragments.R;

import java.util.ArrayList;
import java.util.List;

public class ContactData {

	private List<Contact> contacts = new ArrayList<Contact>();
	public List<Contact> getContacts() {
		return contacts;
	}

	public ContactData() {
		contacts.add(new Contact("Ouija", R.drawable.ouija, 10.95,
				"Rating: 8%"));
		contacts.add(new Contact("Nightcrawler", R.drawable.nightcrawler, 10.95,
				"Rating: 94%"));
		contacts.add(new Contact("Fury", R.drawable.fury, 10.95,
				"Rating: 78%"));
	}


}
