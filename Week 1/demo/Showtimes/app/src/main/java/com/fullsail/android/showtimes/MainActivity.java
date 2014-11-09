package com.scottwaite.android.showtimes;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fundamentals Part 2
Date: November 2, 2014
*/


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



public class MainActivity extends Activity
{
    // Rotten Tomatoes API Key
    private static final String API_KEY = "8k7wm3wtat2p8dj55xm3bmcd";

    // Number of movies per search
    private static final int MOVIE_PAGE_LIMIT = 50;

    private EditText searchBox;
    private Button searchButton;
    private ListView moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (EditText) findViewById(R.id.text_search_box);
        searchButton = (Button) findViewById(R.id.button_search);
        searchButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                new RequestTask().execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" + API_KEY + "&q=" + searchBox.getText().toString().trim() + "&page_limit=" + MOVIE_PAGE_LIMIT);
            }
        });
        moviesList = (ListView) findViewById(R.id.list_movies);
    }

    private void refreshMoviesList(String[] movieTitles)
    {
        moviesList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));
    }

    private class RequestTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... uri)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try
            {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK)
                {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                }
                else
                {
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            }
            catch (Exception e)
            {
                Log.d("Test", "Unable to complete request. Please check connection.");

            }
            return responseString;
        }
        @Override
        protected void onPostExecute(String response)
        {
            super.onPostExecute(response);

            if (response != null)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);

                    JSONArray movies = jsonResponse.getJSONArray("movies");

                    String[] movieTitles = new String[movies.length()];
                    for (int i = 0; i < movies.length(); i++)
                    {
                        JSONObject movie = movies.getJSONObject(i);
                        movieTitles[i] = movie.getString("title");
                    }

                    refreshMoviesList(movieTitles);
                }
                catch (JSONException e)
                {
                    Log.d("Test", "Unable to load the data, please check connection.");
                }
            }
        }
    }
}
