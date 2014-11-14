package com.scottwaite.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scottwaite.android.fragments.data.contact;
import com.scottwaite.android.fragments.data.contactData;

import java.util.List;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/

public class contactListFragment extends ListFragment {

    List<contact> contacts = new contactData().getcontacts();
    private Callbacks activity;

    public contactListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactArrayAdapter adapter = new contactArrayAdapter(getActivity(),
                R.layout.contact_listitem,
                contacts);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_list_fragment, container, false);
        return rootView;
    }

    public interface Callbacks {
        public void onItemSelected(contact contact);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        contact contact = contacts.get(position);
        activity.onItemSelected(contact);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (Callbacks) activity;
    }
}

