package com.sikni8.myapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	private class MyTabListener implements ActionBar.TabListener {

		private Fragment mFragment;
		private final Activity mActivity;
		private final String mFrag;
		

		public MyTabListener( Activity activity, String fragName ) {
			mActivity = activity;
			mFrag = fragName;
		}

		@Override
		public void onTabReselected( Tab tab, FragmentTransaction ft ) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTabSelected( Tab tab, FragmentTransaction ft ) {
			mFragment = Fragment.instantiate( mActivity, mFrag );
			ft.add( android.R.id.content, mFragment );
		}

		@Override
		public void onTabUnselected( Tab tab, FragmentTransaction ft ) {
			ft.remove( mFragment );
			mFragment = null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ActionBar ab = getActionBar();
		ab.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

		Tab tab = ab.newTab()
				.setText( "All Data" )
				.setTabListener( 
						new MyTabListener( this, AllData.class.getName() ) );
		ab.addTab( tab );

		tab = ab.newTab()
				.setText( "Text Data" )
				.setTabListener( 
						new MyTabListener( this, TextData.class.getName() ) );
		ab.addTab( tab );

		tab = ab.newTab()
				.setText( "Image Data" )
				.setTabListener( 
						new MyTabListener( this, ImageData.class.getName() ) );
		ab.addTab( tab );

		if(getIntent().getExtras() != null){ 
			ab.setSelectedNavigationItem(getIntent().getExtras().getInt("tab")); 
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
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_quit:
			moveTaskToBack(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}