package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.Contact;

import java.text.NumberFormat;

public class ContactDetailFragment extends Fragment {

    Contact contact;

    public ContactDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Contact.MOVIE_NAME)) {
            contact = new Contact(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.contact_detail_fragment, container, false);

        if (contact != null) {

            TextView tvName = (TextView) view.findViewById(R.id.tvContactName);
            tvName.setText(contact.getContactName());

            TextView tvInstructions = (TextView) view.findViewById(R.id.tvInstructions);
            tvInstructions.setText(contact.getInstructions());

            ImageView ivPicture = (ImageView) view.findViewById(R.id.ivContactImage);
            ivPicture.setImageResource(contact.getImageResource());

        }

        return view;
    }

}
