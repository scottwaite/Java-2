package com.scottwaite.android.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scottwaite.android.fragments.data.Contact;
import com.scottwaite.android.fragments.data.ContactData;

import java.util.List;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

public class ContactListFragment extends ListFragment {

    List<Contact> contacts = new ContactData().getContacts();
    private Callbacks activity;

    public ContactListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContactArrayAdapter adapter = new ContactArrayAdapter(getActivity(),
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
        public void onItemSelected(Contact contact);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Contact contact = contacts.get(position);
        activity.onItemSelected(contact);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (Callbacks) activity;
    }
}

