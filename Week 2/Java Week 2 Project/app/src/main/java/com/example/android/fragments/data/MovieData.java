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

public class MovieData {

	private List<Movie> movies = new ArrayList<Movie>();
	public List<Movie> getMovies() {
		return movies;
	}

	public MovieData() {
		movies.add(new Movie("Ouija", R.drawable.demo, 15.95,
				"Rating: 86%"));
		movies.add(new Movie("Nightcrawler", R.drawable.demo, 12.95,
				"Rating: 64%"));
		movies.add(new Movie("Fury", R.drawable.demo, 17.95,
				"Rating: 26%"));
	}


}
