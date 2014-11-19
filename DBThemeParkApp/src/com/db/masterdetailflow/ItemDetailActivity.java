package com.db.masterdetailflow;

import com.db.fragments.RideTableFragment;
import com.example.dbthemeparkapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * Activity used only on small screens ---
 * 
 * The screen will show the list of categories to choose from, and
 * when selected will go to the specified fragment
 */
public class ItemDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Saved instance used when the app is closed
        if (savedInstanceState == null) {
        	
            /* Create the detail fragment and add it to the activity 
             * using a fragment transaction. */
            Bundle arguments = new Bundle();
            RideTableFragment fragment = new RideTableFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
