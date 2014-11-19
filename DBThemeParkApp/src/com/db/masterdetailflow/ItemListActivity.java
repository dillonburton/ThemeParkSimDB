package com.db.masterdetailflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.db.fragments.RideTableFragment;
import com.db.fragments.GuestTableFragment;
import com.db.fragments.InfoTableFragment;
import com.example.dbthemeparkapp.R;

/**
 * Activity used on larger screens ---
 * 
 * The screen will show the list of categories to choose from on 
 * the left, and the fragment to the right
 */
public class ItemListActivity extends FragmentActivity
implements ItemListFragment.Callbacks {

	// If the app is running in two pane mode (large screen)
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			mTwoPane = true;

			((ItemListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.item_list))
					.setActivateOnItemClick(true);
		}
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {

		// Arguments for fragments
		Bundle arguments = new Bundle();

		// Current fragment
		Fragment fragment;

		/* If we are on a larger screen, have the fragment to 
		 * the right and the list to the left
		 */
		if (mTwoPane) {

			/* If the rides category was selected, bring up rides fragment */
			if(id.equals("1")){
				fragment = new RideTableFragment();
				fragment.setArguments(arguments);
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment)
				.commit();
			}

			/* If the guests category was selected, bring up guests fragment */
			else if(id.equals("2")){
				fragment = new GuestTableFragment();
				fragment.setArguments(arguments);
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment)
				.commit();
			}

			/* If the info category was selected, bring up info fragment */
			else if(id.equals("3")){
				fragment = new InfoTableFragment();
				fragment.setArguments(arguments);
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.item_detail_container, fragment)
				.commit();
			}

		} else {
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			startActivity(detailIntent);
		}
	}
}
