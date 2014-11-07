package com.scottwaite.android.fragments;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by David on 7/21/2014.
 */
public class MyPrefsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPrefsFragment())
                .commit();
    }
}
