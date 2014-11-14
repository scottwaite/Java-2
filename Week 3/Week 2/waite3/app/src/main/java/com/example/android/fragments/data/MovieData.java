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

public class MovieData {

	private List<Movie> movies = new ArrayList<Movie>();
	public List<Movie> getMovies() {
		return movies;
	}

	public MovieData() {
		movies.add(new Movie("Ouija", R.drawable.ouija, 10.95,
				"Rating: 8%"));
		movies.add(new Movie("Nightcrawler", R.drawable.nightcrawler, 10.95,
				"Rating: 94%"));
		movies.add(new Movie("Fury", R.drawable.fury, 10.95,
				"Rating: 78%"));
	}


}
