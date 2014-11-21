package com.stylingandroid.basicactionbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity
{

	private class MyTabListener implements ActionBar.TabListener
	{
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mFragName;

		public MyTabListener( Activity activity, String fragName )
		{
			mActivity = activity;
			mFragName = fragName;
		}

		@Override
		public void onTabReselected( Tab tab, FragmentTransaction ft )
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected( Tab tab, FragmentTransaction ft )
		{
			mFragment = Fragment.instantiate( mActivity, mFragName );
			ft.add( android.R.id.content, mFragment );
		}

		@Override
		public void onTabUnselected( Tab tab, FragmentTransaction ft )
		{
			ft.remove( mFragment );
			mFragment = null;
		}
	}

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		ActionBar ab = getActionBar();
		setTabNavigation( ab );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		boolean ret;
		if (item.getItemId() == R.id.menu_settings)
		{
			// Handle Settings
			ret = true;
		} else if (item.getItemId() == R.id.menu_toggle)
		{
			ActionBar ab = getActionBar();
			if (ab.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS)
			{
				setListNavigation( ab );
			} else
			{
				setTabNavigation( ab );
			}
			ret = true;
		} else
		{
			ret = super.onOptionsItemSelected( item );
		}
		return ret;
	}

	private void setTabNavigation( ActionBar actionBar )
	{
		actionBar.removeAllTabs();
		actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
		actionBar.setTitle( R.string.app_name );

		Tab tab = actionBar
				.newTab()
				.setText( R.string.frag1 )
				.setTabListener(
						new MyTabListener( this, Fragment1.class.getName() ) );
		actionBar.addTab( tab );

		tab = actionBar
				.newTab()
				.setText( R.string.frag2 )
				.setTabListener(
						new MyTabListener( this, Fragment2.class.getName() ) );
		actionBar.addTab( tab );
	}

	private void setListNavigation( ActionBar actionBar )
	{
		actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_LIST );
		actionBar.setTitle( "" );
		final List<Map<String, Object>> data = 
				new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "title", getString( R.string.frag1 ) );
		map.put( "fragment", Fragment.instantiate( this, Fragment1.class.getName() ));
		data.add( map );
		map = new HashMap<String, Object>();
		map.put( "title", getString( R.string.frag2 ) );
		map.put( "fragment", Fragment.instantiate( this, Fragment2.class.getName() ));
		data.add( map );
		SimpleAdapter adapter = new SimpleAdapter( this, data,
				android.R.layout.simple_spinner_dropdown_item,
				new String[] { "title" }, new int[] { android.R.id.text1 } );
		actionBar.setListNavigationCallbacks( adapter,
				new OnNavigationListener()
				{

					@Override
					public boolean onNavigationItemSelected( int itemPosition,
							long itemId )
					{
						Map<String, Object> map = data.get( itemPosition );
						Object o = map.get( "fragment" );
						if( o instanceof Fragment ) 
						{
							FragmentTransaction tx = getFragmentManager().beginTransaction();
							tx.replace( android.R.id.content, (Fragment )o );
							tx.commit();
						}
						return true;
					}
				} 
		);
	}
}
