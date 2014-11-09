package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scottwaite.android.fragments.data.Movie;

import java.text.NumberFormat;

public class MovieDetailFragment extends Fragment {

    Movie movie;

//    Required no-args constructor
    public MovieDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null && b.containsKey(Movie.MOVIE_NAME)) {
            movie = new Movie(b);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        Load the layout
        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        if (movie != null) {

            //Display values and image
            TextView tvName = (TextView) view.findViewById(R.id.tvMovieName);
            tvName.setText(movie.getMovieName());

            TextView tvInstructions = (TextView) view.findViewById(R.id.tvInstructions);
            tvInstructions.setText(movie.getInstructions());

            NumberFormat fmt = NumberFormat.getCurrencyInstance();
            TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvPrice.setText(fmt.format(movie.getPrice()));

            ImageView ivPicture = (ImageView) view.findViewById(R.id.ivMovieImage);
            ivPicture.setImageResource(movie.getImageResource());

        }

        return view;
    }

}
