package com.scottwaite.android.showtimes;

import android.app.Activity;
import android.os.Bundle;

import com.scottwaite.android.showtimes.fragment.ShowtimeListFragment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);

		if(_savedInstanceState == null) {
			ShowtimeListFragment frag = ShowtimeListFragment.newInstance();
			getFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, frag, ShowtimeListFragment.TAG).commit();
		}
	}
}
