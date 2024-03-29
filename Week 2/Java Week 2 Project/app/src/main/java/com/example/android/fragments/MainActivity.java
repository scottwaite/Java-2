package com.scottwaite.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Fragment & File Fundamentals
Date: November 6, 2014
*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.scottwaite.android.fragments.data.Contact;


public class MainActivity extends Activity
    implements ContactListFragment.Callbacks {

    public static final String MOVIE_BUNDLE = "MOVIE_BUNDLE";
    private static final int REQUEST_CODE = 1001;

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

//            ScreenUtility utility = new ScreenUtility(this);
//            String output = "Width: " + utility.getWidth() + ", " +
//                    "Height: " + utility.getHeight();
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage(output)
//                    .setTitle("Dimensions")
//                    .create()
//                    .show();

            Intent intent = new Intent();
            intent.setClass(this, MyPrefsActivity.class);
            startActivityForResult(intent, 1002);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Contact contact) {
        Bundle b = contact.toBundle();

        if (isTwoPane) {
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(b);
            getFragmentManager().beginTransaction()
                    .replace(R.id.detailContainer, fragment)
                    .commit();
        }
        else {
            Intent intent = new Intent(this, ContactDetailActivity.class);
            intent.putExtra(MOVIE_BUNDLE, b);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1002) {
            SharedPreferences myPrefs =
                    PreferenceManager.getDefaultSharedPreferences(this);
            boolean api_data = myPrefs.getBoolean("api_data", false);
            Toast.makeText(this, "Load from API: " + api_data, Toast.LENGTH_SHORT).show();
        }
    }
}
