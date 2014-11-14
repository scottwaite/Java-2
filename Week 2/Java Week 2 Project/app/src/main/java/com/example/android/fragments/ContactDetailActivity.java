package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class ContactDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
//          Create the fragment, set its args, add it to the detail container
            ContactDetailFragment fragment = new ContactDetailFragment();

            Bundle b = getIntent().getBundleExtra(MainActivity.MOVIE_BUNDLE);
            fragment.setArguments(b);

            getFragmentManager().beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit();
        }

    }

//  Returns to the list activity
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
            finish();
        }
		return true;
	}

}
