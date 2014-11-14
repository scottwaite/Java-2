package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MyPrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }
}
