package com.scottwaite.android.showtimes.fragment;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scottwaite.android.showtimes.R;

public class ShowtimeDetailFragment extends ListFragment {
	
	public static final String TAG = "ShowtimeDetailFragment.TAG";
	
	public static com.scottwaite.android.showtimes.fragment.ShowtimeDetailFragment newInstance() {
		com.scottwaite.android.showtimes.fragment.ShowtimeDetailFragment frag = new com.scottwaite.android.showtimes.fragment.ShowtimeDetailFragment();
		return frag;
	}
	
	@Override
	public void onActivityCreated(Bundle _savedInstanceState) {
		super.onActivityCreated(_savedInstanceState);
		
		String[] showtimes = getResources().getStringArray(R.array.showtimes);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, showtimes);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView _l, View _v, int _position, long _id) {
		String showtime = (String)_l.getItemAtPosition(_position);
		
		new AlertDialog.Builder(getActivity())
		.setTitle(R.string.showtime)
		.setMessage(getString(R.string.selected, showtime))
		.setPositiveButton(R.string.ok, null)
		.show();
	}
}