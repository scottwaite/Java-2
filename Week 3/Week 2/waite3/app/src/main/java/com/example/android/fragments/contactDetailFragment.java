package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.contact;

import java.text.NumberFormat;

public class contactDetailFragment extends Fragment {

    contact contact;

    public contactDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(contact.CONTACT_NAME)) {
            contact = new contact(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.contact_detail_fragment, container, false);

        if (contact != null) {

            TextView tvName = (TextView) view.findViewById(R.id.tvcontactName);
            tvName.setText(contact.getcontactName());

            TextView tvInstructions = (TextView) view.findViewById(R.id.tvInstructions);
            tvInstructions.setText(contact.getInstructions());

            ImageView ivPicture = (ImageView) view.findViewById(R.id.ivcontactImage);
            ivPicture.setImageResource(contact.getImageResource());

        }

        return view;
    }

}
